
package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.CadastroRequestDTO;
import br.com.alunoonline.api.dtos.CadastroResponseDTO;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cadastros")
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroResponseDTO cadastrar(@RequestBody CadastroRequestDTO dados) {

        if (dados.login() == null || dados.login().isBlank()
                || dados.senha() == null || dados.senha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login e senha são obrigatórios");
        }

        if (usuarioRepository.existsByLogin(dados.login())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login já cadastrado");
        }

        Usuario usuario = new Usuario(
                dados.login(),
                passwordEncoder.encode(dados.senha())
        );

        usuarioRepository.save(usuario);

        return new CadastroResponseDTO(usuario.getId(), usuario.getLogin());
    }
}
