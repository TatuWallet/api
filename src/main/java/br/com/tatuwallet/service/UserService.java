package br.com.tatuwallet.service;

import br.com.tatuwallet.entity.User;
import br.com.tatuwallet.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserbyId (UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User save (User userRequestDto) {
        var user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userRepository.save(user);
    }
}
