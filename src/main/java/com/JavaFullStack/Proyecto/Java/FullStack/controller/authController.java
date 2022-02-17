package com.JavaFullStack.Proyecto.Java.FullStack.controller;

import com.JavaFullStack.Proyecto.Java.FullStack.dao.UsuarioDao;
import com.JavaFullStack.Proyecto.Java.FullStack.models.Usuario;
import com.JavaFullStack.Proyecto.Java.FullStack.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "api/login/", method = RequestMethod.POST)
    public String loginUsuario(@RequestBody Usuario usuario){
        Usuario usuarioLogeado = usuarioDao.obtenerUsuarioCredenciales(usuario);
        if(usuarioLogeado != null){
//            en base a los datos de id e email nos devuelve el token
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());
            return tokenJwt;
        };
        return "fail";
    }

}
