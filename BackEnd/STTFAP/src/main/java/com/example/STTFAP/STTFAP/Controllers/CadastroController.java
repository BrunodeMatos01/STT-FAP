package com.example.STTFAP.STTFAP.Controllers;

import com.example.STTFAP.STTFAP.DTO.CadastroRequestDTO;
import com.example.STTFAP.STTFAP.Models.Usuario;
import com.example.STTFAP.STTFAP.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "*")
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CadastroRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha())); // senha criptografada
        novoUsuario.setTipoUsu(dto.getTipoUsu());

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
