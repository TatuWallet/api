package br.com.tatuwallet.service;

import br.com.tatuwallet.dto.response.UserDto;
import br.com.tatuwallet.dto.request.CriarUsuarioRequest;
import br.com.tatuwallet.mapper.UserMapper;
import br.com.tatuwallet.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UserDto criarPerfil(UUID id, CriarUsuarioRequest req) {
        if (repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Perfil já existe");
        }
        return mapper.toDto(repository.save(mapper.toEntity(id, req)));
    }

    @Transactional(readOnly = true)
    public UserDto buscar(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado"));
    }
}
