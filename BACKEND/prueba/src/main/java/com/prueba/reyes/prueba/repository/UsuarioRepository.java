package com.prueba.reyes.prueba.repository;


import com.prueba.reyes.prueba.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
     List<Usuario> findAll();
     Usuario findById(int id);
     Usuario save(Usuario us);
     void delete(Usuario us);


}
