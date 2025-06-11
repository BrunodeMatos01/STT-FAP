package com.example.STTFAP.STTFAP.Models;

import com.example.STTFAP.STTFAP.Enum.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "usuario")

public class Usuario {

    @jakarta.persistence.Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsu;
}
