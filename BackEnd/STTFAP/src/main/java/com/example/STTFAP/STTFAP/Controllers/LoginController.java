package com.example.STTFAP.STTFAP.Controllers;

import com.example.STTFAP.STTFAP.DTO.LoginRequestDTO;
import com.example.STTFAP.STTFAP.Enum.TipoUsuario;
import com.example.STTFAP.STTFAP.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS,
                RequestMethod.PATCH
        }
)
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return usuarioRepository.findByEmail(loginRequestDTO.getEmail())
                .map(usuario -> {
                    if (passwordEncoder.matches(loginRequestDTO.getSenha(), usuario.getSenha())) {
                        return ResponseEntity.ok(Map.of(
                                "message", "Login realizado com sucesso!",
                                "tipoUsu", usuario.getTipoUsu()
                        ));
                    } else {
                        return ResponseEntity.status(401).body("Senha incorreta.");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Usuário não encontrado."));
    }


}
