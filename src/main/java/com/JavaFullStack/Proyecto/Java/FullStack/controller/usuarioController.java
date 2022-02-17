package com.JavaFullStack.Proyecto.Java.FullStack.controller;

import com.JavaFullStack.Proyecto.Java.FullStack.dao.UsuarioDao;
import com.JavaFullStack.Proyecto.Java.FullStack.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class usuarioController {
    @Autowired
    private UsuarioDao usuarioDao;

//    requestmaping usa get por defecto
    @RequestMapping(value = "api/usuarios/", method = RequestMethod.GET)
    public List<Usuario> listarUsuario(){
        return usuarioDao.getUsuarios();
    }
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable Long id){
        usuarioDao.elimUsuario(id);
    }
    @RequestMapping(value = "api/usuarios/", method = RequestMethod.POST)
    public void crearUsuario(@RequestBody Usuario usuario){
        usuarioDao.registrarUsuario(usuario);
    }

}
