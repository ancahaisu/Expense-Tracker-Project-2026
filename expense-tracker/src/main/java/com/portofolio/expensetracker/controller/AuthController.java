package com.portofolio.expensetracker.controller;

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
}