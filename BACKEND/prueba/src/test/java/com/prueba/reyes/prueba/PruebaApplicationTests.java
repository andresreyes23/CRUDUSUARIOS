package com.prueba.reyes.prueba;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.prueba.reyes.prueba.serviceImp.UsuarioServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prueba.reyes.prueba.entity.ResponseUsuario;
import com.prueba.reyes.prueba.entity.Usuario;
import com.prueba.reyes.prueba.repository.UsuarioRepository;
import com.prueba.reyes.prueba.services.UsuarioService;

import Utils.Data;

import java.util.List;

@SpringBootTest
class PruebaApplicationTests {
	
	@Mock
	UsuarioRepository usuarioRepository;

	@Mock
	UsuarioServiceImp usuarioService;
	
	@InjectMocks
	UsuarioServiceImp service;

	@Test
	void contextLoads() {
		when(usuarioRepository.findById(1)).thenReturn(Data.crearUsuario());
		when(usuarioRepository.findById(2)).thenReturn(Data.crearUsuarioNull());
		when(usuarioRepository.findAll()).thenReturn(Data.listaUsuarios);
		
		Usuario us = Data.crearUsuarioservice();
		ResponseUsuario agregado = service.add(us);
		
		assertEquals("Andres", us.getNombre());
		
		assertEquals("leandro", Data.crearUsuario().getNombre(),() -> "el nombre de la persona no es el que se esperaba");
		assertEquals("novoa", Data.crearUsuario().getApellido(),() -> "el apellido de la persona no es el que se esperaba");
		assertEquals("11162783", Data.crearUsuario().getCedula(),() -> "la cedula de la persona no es el que se esperaba");
		assertEquals("leandro@gmail.com", Data.crearUsuario().getCorreo(),() -> "el correo de la persona no es el que se esperaba");
		assertNotNull(Data.crearUsuario().getCedula(),() -> "la cedual esta nula");
		assertNull(Data.crearUsuarioNull().getCedula());
		verify(usuarioRepository,never()).findById(1);
		verify(usuarioRepository).findAll();
		
	}

	@Test
	void testGuardarUsuario() {
		Usuario us = Data.crearUsuarioservice();
		when(usuarioRepository.save(any(Usuario.class))).then(new Answer<Usuario>() {

			Integer secuencia = 4;

			@Override
			public Usuario answer(InvocationOnMock invocation) throws Throwable {
				Usuario usuario = invocation.getArgument(0);
				usuario.setId(secuencia++);
				return usuario;
			}
		});
		ResponseUsuario agregado = service.add(us);
		assertNotNull(us.getId());
		assertEquals("200",agregado.getStatus());
		assertEquals("procesado correctamente",agregado.getMensaje());
		assertEquals(4,us.getId());
		assertEquals("Andres",us.getNombre());
		assertEquals(Data.crearUsuarioservice().getCorreo(),us.getCorreo());
		assertEquals(Data.crearUsuarioservice().getApellido(),us.getApellido());
		assertEquals(Data.crearUsuarioservice().getCedula(),us.getCedula());
		assertNotEquals(Data.crearUsuarioNull().getCedula(),us.getCedula());
		assertNull(null,Data.crearUsuarioNull().getCedula());
		verify(usuarioRepository).save(any(Usuario.class));
	}

	@Test
	void testEditarUsuario() {
		Usuario us = Data.crearUsuarioservice();
		when(usuarioRepository.save(any(Usuario.class))).then(new Answer<Usuario>() {

			Integer secuencia = 4;

			@Override
			public Usuario answer(InvocationOnMock invocation) throws Throwable {
				Usuario usuario = invocation.getArgument(0);
				usuario.setId(secuencia++);
				return usuario;
			}
		});
		ResponseUsuario editar = service.edit(us);
		assertNotNull(us.getId());
		assertEquals(4,us.getId());
		assertEquals("Andres",us.getNombre());
		assertEquals(Data.crearUsuarioservice().getCorreo(),us.getCorreo());
		assertEquals(Data.crearUsuarioservice().getApellido(),us.getApellido());
		assertEquals(Data.crearUsuarioservice().getCedula(),us.getCedula());
		assertNotEquals(Data.crearUsuarioNull().getCedula(),us.getCedula());
		assertNull(null,Data.crearUsuarioNull().getCedula());
		assertEquals("200",editar.getStatus());
		assertEquals("procesado correctamente",editar.getMensaje());
		verify(usuarioRepository).save(any(Usuario.class));
	}

	@Test
	void testBuscarAll() {
		when(usuarioRepository.findAll()).thenReturn(Data.listaUsuarios);
		List<Usuario> listaUsuarios = service.listar();
		assertNotNull(listaUsuarios);
		assertNotEquals(Data.crearUsuarioNull().getCedula(),listaUsuarios.get(2).getCedula());
		assertEquals(Data.crearUsuario().getId(),listaUsuarios.get(0).getId());
		verify(usuarioRepository).findAll();
	}

	@Test
	void testBuscarById() {
		Usuario usuario = Data.crearUsuario();
		when(usuarioRepository.findById(1)).thenReturn(Data.crearUsuario());
		Usuario user = service.listarId(usuario.getId());
		assertEquals(usuario.getId(),user.getId());
		assertNotEquals("Andres",user.getNombre());
		verify(usuarioRepository).findById(1);
		verify(usuarioRepository,never()).findAll();
	}

	@Test
	void testValidarTelefono() {
		Usuario usuario = Data.crearUsuario();

		Boolean respuesta = service.validacionNumtel(usuario);
		Boolean respuesta2 = service.validacionNumtel(Data.crearUsuariotel());
		assertEquals(true,respuesta);
		assertEquals(false,respuesta2);

	}

	@Test
	void testValidarCedula() {
		Usuario usuario = Data.crearUsuario();

		Boolean respuesta = service.validacionNumced(usuario);
		Boolean respuesta2 = service.validacionNumced(Data.crearUsuarioced());
		assertEquals(true,respuesta);
		assertEquals(false,respuesta2);

	}

	@Test
	void testValidarCorreoExiste() {
		Usuario usuario = Data.crearUsuario();
		when(usuarioRepository.findAll()).thenReturn(Data.listaUsuarios);
		Boolean respuesta = service.ValidacionCorreoExitente(usuario);
		Boolean respuesta2 = service.ValidacionCorreoExitente(Data.crearUsuariocorreo());
		assertEquals(true,respuesta);
		assertEquals(false,respuesta2);
		verify(usuarioRepository,times(2)).findAll();

	}

	@Test
	void testValidarCedulaExiste() {
		Usuario usuario = Data.crearUsuario();
		when(usuarioRepository.findAll()).thenReturn(Data.listaUsuarios);
		Boolean respuesta = service.ValidacionCedulaExitente(usuario);
		Boolean respuesta2 = service.ValidacionCedulaExitente(Data.crearUsuariocorreo());
		assertEquals(true,respuesta);
		assertEquals(false,respuesta2);
		verify(usuarioRepository,times(2)).findAll();

	}

	@Test
	void testValidarCorreoExisteup() {
		Usuario usuario = Data.crearUsuario();
		when(usuarioRepository.findAll()).thenReturn(Data.listaUsuarios);
		Boolean respuesta = service.ValidacionCorreoExitenteup(usuario);
		Boolean respuesta2 = service.ValidacionCorreoExitenteup(Data.crearUsuariocorreoup());
		assertEquals(false,respuesta);
		assertEquals(false,respuesta2);
		verify(usuarioRepository,times(2)).findAll();

	}

	@Test
	void testValidarCedulaExisteup() {
		Usuario usuario = Data.crearUsuario();
		when(usuarioRepository.findAll()).thenReturn(Data.listaUsuarios);
		Boolean respuesta = service.ValidacionCedulaExitenteup(usuario);
		Boolean respuesta2 = service.ValidacionCedulaExitenteup(Data.crearUsuariocorreo());
		assertEquals(false,respuesta);
		assertEquals(false,respuesta2);
		verify(usuarioRepository,times(2)).findAll();

	}

	@Test
	void trstValidarFormatoCorreo() {
		Usuario usuario = Data.crearUsuario();
		Usuario usuario2 = Data.crearUsuarioFormatoCorreo();

		when(usuarioRepository.findById(1)).thenReturn(Data.crearUsuario());
		when(usuarioRepository.findById(1)).thenReturn(Data.crearUsuarioFormatoCorreo());
		Boolean res = service.ValidarFormatoCorreo(usuario);
		Boolean res2 = service.ValidarFormatoCorreo(usuario2);
		assertEquals(true,res);
		assertEquals(false,res2);
		verify(usuarioRepository,never()).findById(1);
	}
}
