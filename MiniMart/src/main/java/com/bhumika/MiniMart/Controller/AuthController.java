package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Dto.LoginDto;
import com.bhumika.MiniMart.Dto.UserDto;

import com.bhumika.MiniMart.Service.AuthServiceImpl;
import com.bhumika.MiniMart.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserService userService;

    // ---- Register ----
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
//        UserDto savedUser = userService.registerUser(userDto); // password encode hoga service me
//        return ResponseEntity.ok(savedUser);
//    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto saved = authService.register(userDto);
        return ResponseEntity.ok(saved);
    }

    // ---- Login ----
//    @PostMapping("/login")
//    public String login(@RequestBody LoginDto loginDto) {
//        return userService.login(loginDto);
//    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto); // ab ye token return karega
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }


}

