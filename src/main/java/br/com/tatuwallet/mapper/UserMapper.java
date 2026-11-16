package br.com.tatuwallet.mapper;

import br.com.tatuwallet.dto.response.UserDto;
import br.com.tatuwallet.dto.request.CriarUsuarioRequest;
import br.com.tatuwallet.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User toEntity(CriarUsuarioRequest req) {
        User u = new User();
        u.setName(req.name());
        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setActive(true);
        return u;
    }

    public UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getName(), u.getUsername(), u.getEmail(),
                u.getDataCadastro(), u.getActive());
    }
}