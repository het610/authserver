package com.example.auth2server.controller;

import com.example.auth2server.controller.dto.UserDto;
import com.example.auth2server.model.AuthUser;
import com.example.auth2server.repo.UserRepo;
import com.example.auth2server.repo.UserRoleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthUser register(@RequestBody UserDto userDto ){
        AuthUser authUser = new ObjectMapper().convertValue(userDto, AuthUser.class);

        authUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        authUser.setAuthRoles(Collections.singletonList(userRoleRepo.findByRoleNameContaining("USER")));

        Optional<AuthUser> userOpt =  userRepo.findByUserNameOrEmail(userDto.getUserName(), userDto.getEmail());
        if(!userOpt.isPresent()) {
            userRepo.save(authUser);
        }
        throw new RuntimeException("User already exist");
    }
}

