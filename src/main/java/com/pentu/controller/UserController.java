package com.pentu.controller;

import com.pentu.controller.base.RestApiResp;
import com.pentu.domain.User;
import com.pentu.dto.UserDto;
import com.pentu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserRepository userRespoistory  ;
    private PasswordEncoder PasswordEncoder;

    public UserController(UserRepository userRespoistory,
                          PasswordEncoder encoder){
        this.userRespoistory = userRespoistory;
        this.PasswordEncoder = encoder;
    }


    @PostMapping
    public RestApiResp add(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(this.PasswordEncoder.encode(userDto.getPassword()));

        userRespoistory.save(user);

        return new RestApiResp();
    }

    @GetMapping
    public RestApiResp list(){
        userRespoistory.findAll();

        return new RestApiResp();
    }

    @GetMapping(value="/{id}")
    public RestApiResp get(@PathVariable int id){
        userRespoistory.findById(id);

        return new RestApiResp();
    }

    @PostMapping(value="public/singup")
    public RestApiResp singup(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(this.PasswordEncoder.encode(userDto.getPassword()));

        userRespoistory.save(user);

        return new RestApiResp();
    }
}
