package com.yasmiongv.autentication_api.service;

import com.yasmiongv.autentication_api.controller.dtos.UserDTO;
import com.yasmiongv.autentication_api.controller.mappers.UserMapper;

import com.yasmiongv.autentication_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public UserService(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void register(UserDTO user) {
        var entity = mapper.toEntity(user);
        repository.save(entity);
    }
}
