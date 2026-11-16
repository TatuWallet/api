package br.com.tatuwallet.controller;

import br.com.tatuwallet.dto.response.UserDto;
import br.com.tatuwallet.dto.request.CriarUsuarioRequest;
import br.com.tatuwallet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> criar(@Valid @RequestBody CriarUsuarioRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.criarPerfil(req));
    }

    @GetMapping("/{id}")
    public UserDto buscar(@PathVariable UUID id) {
        return userService.buscar(id);
    }
}