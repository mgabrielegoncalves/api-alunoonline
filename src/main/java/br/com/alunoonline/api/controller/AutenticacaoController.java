package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.AutenticacaoRequestDTO;
import br.com.alunoonline.api.dtos.TokenResponseDTO;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AutenticacaoController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody AutenticacaoRequestDTO dados) {
        if (dados.login() == null || dados.login().isBlank() || dados.senha() == null || dados.senha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login e senha são obrigatórios");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()));
            return new TokenResponseDTO(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
        } catch (org.springframework.security.core.AuthenticationException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login ou senha inválidos");
        }
    }
}
