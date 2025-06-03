package com.example.STTFAP.STTFAP.Controllers;

import com.example.STTFAP.STTFAP.DTO.CadastroRequestDTO;
import com.example.STTFAP.STTFAP.Models.Usuario;
import com.example.STTFAP.STTFAP.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "*")
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CadastroRequestDTO cadastroDTO) {

        if (usuarioRepository.findByEmail(cadastroDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("erro", "E-mail já está em uso."));
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(cadastroDTO.getNome());
        novoUsuario.setEmail(cadastroDTO.getEmail());
        novoUsuario.setSenha(cadastroDTO.getSenha());
        novoUsuario.setTipoUsu(cadastroDTO.getTipoUsu());

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok(Map.of("mensagem", "Usuário cadastrado com sucesso!"));
    }
}
