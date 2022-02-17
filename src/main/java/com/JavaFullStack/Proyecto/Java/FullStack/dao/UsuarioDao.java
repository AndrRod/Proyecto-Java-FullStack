package com.JavaFullStack.Proyecto.Java.FullStack.dao;


import com.JavaFullStack.Proyecto.Java.FullStack.models.Usuario;

import java.util.List;


public interface UsuarioDao {
    List<Usuario> getUsuarios();
    void elimUsuario(Long id);

    void registrarUsuario(Usuario usuario);

    boolean verificarMailYPassword(Usuario usuario);
}
