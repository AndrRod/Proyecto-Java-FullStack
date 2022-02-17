package com.JavaFullStack.Proyecto.Java.FullStack.controller;

import com.JavaFullStack.Proyecto.Java.FullStack.dao.UsuarioDao;
import com.JavaFullStack.Proyecto.Java.FullStack.models.Usuario;
import com.JavaFullStack.Proyecto.Java.FullStack.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class usuarioController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;
//    requestmaping usa get por defecto
    @RequestMapping(value = "api/usuarios/", method = RequestMethod.GET)
    public List<Usuario> listarUsuario(@RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){return null;}
        return usuarioDao.getUsuarios();
    }


//    para validar true o false si el token existe acorde a algun usuario
    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if(validarToken(token)){
            usuarioDao.elimUsuario(id);
        }
    }
    @RequestMapping(value = "api/usuarios/", method = RequestMethod.POST)
    public void crearUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrarUsuario(usuario);
    }

}
