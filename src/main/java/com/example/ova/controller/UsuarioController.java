package com.example.ova.controller;

import com.example.ova.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ova.services.UsuarioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    public final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    Map<String, String> errorResponse = new HashMap<>();

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario user){
        errorResponse.clear();
        Map<String, Object> usuario = usuarioService.loginUsuario(user.getCorreo(), user.getPassword());
        if(!usuario.isEmpty()){
            return ResponseEntity.ok(usuario);
        }else {
            errorResponse.put("message", "Login incorrecto. Por favor verifique sus datos");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario){
        errorResponse.clear();
        String message = usuarioService.saveUsuario(usuario);

        if(message != null){
            errorResponse.put("message", message);
            return ResponseEntity.ok(errorResponse);
        }else{
            errorResponse.put("message", "Hubo un error al crear la persona");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/list")
    public List<Usuario> usuarioList(){
        return usuarioService.usuarioList();
    }

    @GetMapping("/cursando/{correo}")
    public Object cursando(@PathVariable String correo){
        return usuarioService.cursando(correo);
    }
}
