package br.com.tatuwallet.mapper;

import br.com.tatuwallet.dto.response.UserDto;
import br.com.tatuwallet.dto.request.CriarUsuarioRequest;
import br.com.tatuwallet.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User toEntity(UUID id, CriarUsuarioRequest req) {
        User u = new User();
        u.setId(id);
        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setActive(true);
        return u;
    }

    public UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getUsername(), u.getEmail(),
                u.getDataCadastro(), u.getActive());
    }
}