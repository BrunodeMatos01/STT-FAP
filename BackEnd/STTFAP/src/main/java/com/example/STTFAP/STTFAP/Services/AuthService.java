package com.example.STTFAP.STTFAP.Services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean autenticar(String email, String senha) {
        return email.equals("brunodematos3@gmail.com") && senha.equals("teste");
    }
}
