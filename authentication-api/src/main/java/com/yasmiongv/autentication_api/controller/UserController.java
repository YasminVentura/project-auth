package com.yasmiongv.autentication_api.controller;

import com.yasmiongv.autentication_api.controller.dtos.LoginRequest;
import com.yasmiongv.autentication_api.controller.dtos.LoginResponse;
import com.yasmiongv.autentication_api.controller.dtos.UserDTO;
import com.yasmiongv.autentication_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO user){
        service.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var user = service.findByEmail(request.email());

        if (user.isEmpty() || !user.get().getPassword().equals(request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = service.generateJwtToken(user.get());
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
