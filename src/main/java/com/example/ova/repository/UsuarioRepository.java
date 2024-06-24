package com.example.ova.repository;

import com.example.ova.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCorreoAndPassword(String correo, String password);

    @Query(value = "select STRING_AGG(CAST(uc.id_curso AS TEXT), ', ' ORDER BY uc.id_curso) as cursos from usuario_curso uc " +
            "join usuario u ON uc.id_usuario = u.id " +
            "where u.correo = :correo", nativeQuery = true)
    List<Object> findByCorreo(String correo);
}
