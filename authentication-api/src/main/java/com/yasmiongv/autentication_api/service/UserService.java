package com.yasmiongv.autentication_api.service;

import com.yasmiongv.autentication_api.controller.dtos.UserDTO;
import com.yasmiongv.autentication_api.controller.mappers.UserMapper;

import com.yasmiongv.autentication_api.entity.User;
import com.yasmiongv.autentication_api.exceptions.custom.DuplicateEmailException;
import com.yasmiongv.autentication_api.repositories.UserRepository;
import com.yasmiongv.autentication_api.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    public UserService(UserMapper mapper, UserRepository repository, JwtUtil jwtUtil) {
        this.mapper = mapper;
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public void register(UserDTO user) {
        if(repository.existsByEmail(user.email())) {
            throw new DuplicateEmailException("Email is already registered");
        }

        var entity = mapper.toEntity(user);
        repository.save(entity);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String generateJwtToken(User user) {
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }
}
