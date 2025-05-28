package com.example.STTFAP.STTFAP.Controllers;

import com.example.STTFAP.STTFAP.DTO.LoginRequestDTO;
import com.example.STTFAP.STTFAP.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return usuarioRepository.findByEmail(loginRequestDTO.getEmail())
                .map(usuario -> {
                    if (usuario.getSenha().equals(loginRequestDTO.getSenha())) {
                        return ResponseEntity.ok("Login realizado com sucesso!");
                    } else {
                        return ResponseEntity.status(401).body("Senha incorreta.");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Usuário não encontrado."));
    }
}