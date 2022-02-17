package com.JavaFullStack.Proyecto.Java.FullStack.controller;

import com.JavaFullStack.Proyecto.Java.FullStack.dao.UsuarioDao;
import com.JavaFullStack.Proyecto.Java.FullStack.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {
    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value = "api/login/", method = RequestMethod.POST)
    public String loginUsuario(@RequestBody Usuario usuario){
        if(usuarioDao.verificarMailYPassword(usuario)){
            return "ok";
        };
        return "fail";
    }
}
