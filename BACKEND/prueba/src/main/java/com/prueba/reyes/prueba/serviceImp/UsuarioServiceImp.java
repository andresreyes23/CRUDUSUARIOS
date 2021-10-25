package com.prueba.reyes.prueba.serviceImp;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.reyes.prueba.entity.ResponseUsuario;
import com.prueba.reyes.prueba.entity.Usuario;
import com.prueba.reyes.prueba.exceptions.NoletrasException;
import com.prueba.reyes.prueba.repository.UsuarioRepository;
import com.prueba.reyes.prueba.services.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario listarId(int id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	public ResponseUsuario add(Usuario us) {
		return validacionesRespusta(us);
	}
	
	

	@Override
	public ResponseUsuario edit(Usuario us) {
		// TODO Auto-generated method stub
		return validacionesRespusta2(us);
	}

	@Override
	public Usuario delete(int id) {
		
		Usuario u = usuarioRepository.findById(id);
        if(u != null){
            usuarioRepository.delete(u);
        }
        return u;
	}
	
	public ResponseUsuario validacionesRespusta(Usuario us) {
		ResponseUsuario res = new ResponseUsuario();
		if(validacionNumtel(us) != true) {
				res.setStatus("500");
				res.setMensaje("el numero de telefono no debe tener letras");
				return res;
		}else if(validacionNumced(us) != true) {
			res.setStatus("500");
			res.setMensaje("la cedula no debe tener letras");
			return res;
	    }
		else if(ValidacionCorreoExitente(us) == true) {
			res.setStatus("500");
			res.setMensaje("este correo ya existe en los registros");
			return res;
		}
		else if(ValidarFormatoCorreo(us) != true) {
			res.setStatus("500");
			res.setMensaje("el formato del correo es invalido");
			return res;
		}
		else if(ValidacionCedulaExitente(us) == true) {
			res.setStatus("500");
			res.setMensaje("esta cedula ya existe en los registros");
			return res;
		}
		
		else {
			 usuarioRepository.save(us);
			 res.setStatus("200");
				res.setMensaje("procesado correctamente");
				return res;
		}
	}
	
	public ResponseUsuario validacionesRespusta2(Usuario us) {
		ResponseUsuario res = new ResponseUsuario();
		if(validacionNumtel(us) != true) {
				res.setStatus("500");
				res.setMensaje("el numero de telefono no debe tener letras");
				return res;
		}else if(validacionNumced(us) != true) {
			res.setStatus("500");
			res.setMensaje("la cedula no debe tener letras");
			return res;
	    }
		else if(ValidarFormatoCorreo(us) != true) {
			res.setStatus("500");
			res.setMensaje("el formato del correo es invalido");
			return res;
		}
		else if(ValidacionCorreoExitenteup(us) == true) {
			res.setStatus("500");
			res.setMensaje("este correo ya existe en los registros");
			return res;
		}
		else if(ValidacionCedulaExitenteup(us) == true) {
			res.setStatus("500");
			res.setMensaje("esta cedula ya existe en los registros");
			return res;
		}
		
		else {
			 usuarioRepository.save(us);
			 res.setStatus("200");
				res.setMensaje("procesado correctamente");
				return res;
		}
	}
	
	  public Boolean validacionNumtel(Usuario user) {
	        boolean isNumericTel =  user.getTelefono().matches("[+-]?\\d*(\\.\\d+)?");
	        if (isNumericTel == true) {
				return true;
			}else {
				return false;
			}
	    }
	  
	  public Boolean validacionNumced(Usuario user) {
	        boolean isNumericCed =  user.getCedula().matches("[+-]?\\d*(\\.\\d+)?");
	        if (isNumericCed == true) {
				return true;
			}else {
				return false;
			}
	    }
	  
	  public Boolean ValidacionCorreoExitente(Usuario user) {
		  List<Usuario> Usuarioslis = usuarioRepository.findAll();
		  boolean respuesta = false;
		  int cont = 0;
		  for (int i = 0; i < Usuarioslis.size(); i++) {
			  if (user.getCorreo().equals(Usuarioslis.get(i).getCorreo())) {
				  cont = cont + 1;	
			} 
		}
		  if (cont > 0) {
			respuesta = true;
		}
		  return respuesta;
	  }
	  
	  public Boolean ValidacionCedulaExitente(Usuario user) {
		  List<Usuario> Usuarioslis = usuarioRepository.findAll();
		  boolean respuesta = false;
		  int cont = 0;
		  for (int i = 0; i < Usuarioslis.size(); i++) {
			  if (user.getCedula().equals(Usuarioslis.get(i).getCedula())) {
				  cont = cont + 1;	
			} 
		}
		  if (cont > 0) {
			respuesta = true;
		}
		  return respuesta;
	  }
	  
	  public Boolean ValidacionCorreoExitenteup(Usuario user) {
		  List<Usuario> Usuarioslis = usuarioRepository.findAll();
		  boolean respuesta = false;
		  int cont = 0;
		  for (int i = 0; i < Usuarioslis.size(); i++) {
			  if (user.getCorreo().equals(Usuarioslis.get(i).getCorreo()) && !user.getId().equals(Usuarioslis.get(i).getId())) {
				  cont = cont + 1;	
			} 
		}
		  if (cont > 0) {
			respuesta = true;
		}
		  return respuesta;
	  }
	  
	  public Boolean ValidacionCedulaExitenteup(Usuario user) {
		  List<Usuario> Usuarioslis = usuarioRepository.findAll();
		  boolean respuesta = false;
		  int cont = 0;
		  for (int i = 0; i < Usuarioslis.size(); i++) {
			  if (user.getCedula().equals(Usuarioslis.get(i).getCedula()) && !user.getId().equals(Usuarioslis.get(i).getId())) {
				  cont = cont + 1;	
			} 
		}
		  if (cont > 0) {
			respuesta = true;
		}
		  return respuesta;
	  }
	  
	  public Boolean ValidarFormatoCorreo(Usuario user) {
		
		// Patrón para validar el email
	        Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	        
	        Matcher matcher = pattern.matcher(user.getCorreo());
	       
	        return matcher.find();
	  }

}
