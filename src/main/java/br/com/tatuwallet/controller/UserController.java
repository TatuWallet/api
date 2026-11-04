package br.com.tatuwallet.controller;

import br.com.tatuwallet.dto.UserRequestDto;
import br.com.tatuwallet.entity.User;
import br.com.tatuwallet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> save (@Valid @RequestBody UserRequestDto userRequestDto) {
        var response = new User();
        BeanUtils.copyProperties(userRequestDto, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(response));
    }


}
