package com.JavaFullStack.Proyecto.Java.FullStack.implementacion;

import com.JavaFullStack.Proyecto.Java.FullStack.models.Usuario;
import com.JavaFullStack.Proyecto.Java.FullStack.dao.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
// transaccional da el acceso por medio de consultas a la base de datos
@Transactional
public class usuarioImpl implements UsuarioDao {

    // nos sirve par hacer la consulta
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void elimUsuario(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }
    @Override
    public void registrarUsuario(Usuario usuario) {
//        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
//        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
//        usuario.setPassword(hash);
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioCredenciales(Usuario usuario) {
        // de esta manera se evita injeccion sql de posibles hackers
        String query = "FROM Usuario WHERE email = :email";
//        AND password = :password";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
//                .setParameter("password", usuario.getPassword())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;

//        return !lista.isEmpty();
        //        Es lo mismo que la ultima linea
//        if(lista.isEmpty()){return false;}
//        return true;
    }
}
