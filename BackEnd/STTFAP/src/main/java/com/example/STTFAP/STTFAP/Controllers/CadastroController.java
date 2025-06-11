package com.example.STTFAP.STTFAP.Controllers;

import com.example.STTFAP.STTFAP.DTO.CadastroRequestDTO;
import com.example.STTFAP.STTFAP.Models.Usuario;
import com.example.STTFAP.STTFAP.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "*", allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PATCH })
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody CadastroRequestDTO dto) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setTipoUsu(dto.getTipoUsu());

        try {
            usuarioRepository.save(novoUsuario);
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        }
        catch (DataIntegrityViolationException ex) {
            // aqui cai se email já existir (violação de unique constraint)
            return ResponseEntity.badRequest().body("Erro: e-mail já cadastrado.");
        }
        catch (Exception ex) {
            // outro erro genérico
            return ResponseEntity.status(500).body("Erro interno ao cadastrar.");
        }
    }
}
