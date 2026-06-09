package com.portofolio.expensetracker.service;

import com.portofolio.expensetracker.dto.RegisterUserRequest;
import com.portofolio.expensetracker.entity.Expense;
import com.portofolio.expensetracker.entity.User;
import com.portofolio.expensetracker.repository.UserRepository;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;

    public User register(RegisterUserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }
}