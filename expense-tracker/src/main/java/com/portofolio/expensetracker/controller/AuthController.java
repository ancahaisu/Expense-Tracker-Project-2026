package com.portofolio.expensetracker.controller;

import com.portofolio.expensetracker.dto.LoginRequest;
import com.portofolio.expensetracker.dto.LoginResponse;
import com.portofolio.expensetracker.dto.RegisterUserRequest;
import com.portofolio.expensetracker.dto.UserResponse;
import com.portofolio.expensetracker.entity.User;
import com.portofolio.expensetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterUserRequest request) {
        User savedUser = userService.register(request);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        String token = userService.login(request);

        return new LoginResponse(token);
    }
}