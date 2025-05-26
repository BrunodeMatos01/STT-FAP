package com.example.Controllers;

import com.example.DTO.LoginRequestDTO;
import com.example.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public String login(@RequestBody LoginRequestDTO loginRequest) {
        boolean autenticado = authService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());
        return autenticado ? "Login bem-sucedido!" : "Credenciais inválidas.";
    }
}
