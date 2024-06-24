package com.example.ova.services;

import com.example.ova.entity.Usuario;
import org.springframework.stereotype.Service;
import com.example.ova.repository.UsuarioRepository;

import java.util.*;

@Service
public class UsuarioService {

    final
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    Map<String, Object> datos = new LinkedHashMap<>();

    public Map<String, Object> loginUsuario(String correo, String password){
        datos.clear();
        Usuario usuario = usuarioRepository.findByCorreoAndPassword(correo, password);

        datos.put("nombre", usuario.getNombre());
        datos.put("correo", usuario.getCorreo());
        datos.put("telefono", usuario.getTelefono());
        datos.put("registro", usuario.getFechaRegistro());
        datos.put("password", usuario.getPassword());

        return datos;
    }

    public String saveUsuario(Usuario usuario){
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty() ||
                usuario.getPassword() == null || usuario.getPassword().trim().isEmpty() ||
                usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty() ||
                usuario.getTelefono() == null || usuario.getTelefono().trim().isEmpty()){
            return null;
        }else{
            Date fechaRegistro = new Date();
            usuario.setFechaRegistro(fechaRegistro);
            usuarioRepository.save(usuario);

            return "Usuario creado con éxito";
        }
    }

    public List<Usuario> usuarioList(){
        return usuarioRepository.findAll();
    }

    public Object cursando(String correo){
        return usuarioRepository.findByCorreo(correo);
    }
}
