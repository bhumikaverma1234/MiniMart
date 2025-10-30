package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Dto.LoginDto;
import com.bhumika.MiniMart.Dto.UserDto;

import com.bhumika.MiniMart.Service.AuthServiceImpl;
import com.bhumika.MiniMart.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Authentication", description = "APIs for registering users/admins and logging in to get JWT tokens")
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Register a new account",
            description = "Registers a new user or admin. Pass role (USER/ADMIN) in the request body to set the role."
    )
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto saved = authService.register(userDto);
        return ResponseEntity.ok(saved);
    }

    @Operation(
            summary = "Login",
            description = "Authenticates user or admin and returns a JWT token on successful login."
    )
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto); // ab ye token return karega
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }


}

