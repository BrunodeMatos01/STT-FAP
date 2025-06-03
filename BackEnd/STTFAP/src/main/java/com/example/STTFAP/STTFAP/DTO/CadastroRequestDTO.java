package com.example.STTFAP.STTFAP.DTO;

import com.example.STTFAP.STTFAP.Enum.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsu; // agora é enum
}
