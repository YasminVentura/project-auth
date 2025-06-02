package com.yasmiongv.autentication_api.controller;

import com.yasmiongv.autentication_api.controller.dtos.LoginRequest;
import com.yasmiongv.autentication_api.controller.dtos.LoginResponse;
import com.yasmiongv.autentication_api.controller.dtos.UserDTO;
import com.yasmiongv.autentication_api.exceptions.ErrorResponse;
import com.yasmiongv.autentication_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "User Controller", description = "User operations")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Registers a new user in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or duplicated email",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO user){
        service.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var user = service.findByEmail(request.email());

        if (user.isEmpty() || !user.get().getPassword().equals(request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = service.generateJwtToken(user.get());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/user/dashboard")
    @Operation(summary = "User dashboard", description = "Returns a message for authenticated users (user or admin role)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hello, user!"),
            @ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Hello, user!");
    }

    @GetMapping("/admin/panel")
    @Operation(summary = "Admin panel", description = "Returns a message for authenticated admin users only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hello, admin!"),
            @ApiResponse(responseCode = "403", description = "Forbidden - admin access required")
    })
    public ResponseEntity<String> adminPanel() {
        return ResponseEntity.ok("Hello, admin!");
    }
}
