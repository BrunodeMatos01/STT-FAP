package com.example.STTFAP.STTFAP.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private String tipoUsu;
}
