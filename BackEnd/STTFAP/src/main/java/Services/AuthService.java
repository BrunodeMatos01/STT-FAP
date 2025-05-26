package com.example.Services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean autenticar(String email, String senha) {
        // Exemplo fixo - pode substituir por consulta ao banco de dados depois
        return email.equals("admin@email.com") && senha.equals("123456");
    }
}
