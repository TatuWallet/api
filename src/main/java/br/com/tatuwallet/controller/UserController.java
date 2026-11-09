package br.com.tatuwallet.controller;

import br.com.tatuwallet.dto.response.UserDto;
import br.com.tatuwallet.dto.request.CriarUsuarioRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public class UserController {

    @PostMapping("/usuarios/me")
    public ResponseEntity<UserDto> criarPerfil(@AuthenticationPrincipal Jwt jwt,
                                               @Valid @RequestBody CriarUsuarioRequest req) {
        UUID id = UUID.fromString(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPerfil(id, req));
    }
}
