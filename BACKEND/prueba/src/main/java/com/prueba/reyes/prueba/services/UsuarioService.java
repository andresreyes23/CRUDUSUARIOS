package com.prueba.reyes.prueba.services;

import com.prueba.reyes.prueba.entity.ResponseUsuario;
import com.prueba.reyes.prueba.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario>listar();
    Usuario listarId(int id);
    ResponseUsuario add(Usuario us);
    ResponseUsuario edit(Usuario us);
    Usuario delete(int id);
}
