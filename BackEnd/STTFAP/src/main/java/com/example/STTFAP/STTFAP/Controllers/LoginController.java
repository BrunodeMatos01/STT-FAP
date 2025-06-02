package com.example.STTFAP.STTFAP.Controllers;

import com.example.STTFAP.STTFAP.DTO.LoginRequestDTO;
import com.example.STTFAP.STTFAP.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return usuarioRepository.findByEmail(loginRequestDTO.getEmail())
                .map(usuario -> {
                    if (usuario.getSenha().equals(loginRequestDTO.getSenha())) {
                        return ResponseEntity.ok(Map.of("message", "Login realizado com sucesso!", "tipoUsu", usuario.getTipoUsu()));
                    } else {
                        return ResponseEntity.status(401).body("Senha incorreta.");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Usuário não encontrado."));
    }

    @GetMapping("/admin-area")
    public ResponseEntity<?> adminArea(@RequestParam String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuario -> {
                    if (!usuario.getTipoUsu().equalsIgnoreCase("admin")) {
                        return ResponseEntity.status(403).body("Acesso negado: apenas administradores.");
                    }
                    return ResponseEntity.ok("Bem-vindo à área administrativa!");
                })
                .orElse(ResponseEntity.status(404).body("Usuário não encontrado."));
    }
}