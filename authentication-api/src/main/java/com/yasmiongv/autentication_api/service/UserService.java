package com.yasmiongv.autentication_api.service;

import com.yasmiongv.autentication_api.controller.dtos.UserDTO;
import com.yasmiongv.autentication_api.controller.mappers.UserMapper;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public UserDTO register(UserDTO user) {
        var entity = mapper.toEntity(user);
        return user;
    }
}
