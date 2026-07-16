package com.portofolio.expensetracker.service;

import com.portofolio.expensetracker.dto.CreateExpenseRequest;
import com.portofolio.expensetracker.dto.ExpenseResponse;
import com.portofolio.expensetracker.dto.UpdateExpenseRequest;
import com.portofolio.expensetracker.entity.Expense;
import com.portofolio.expensetracker.entity.User;
import com.portofolio.expensetracker.repository.ExpenseRepository;
import com.portofolio.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    public ExpenseResponse create(
            CreateExpenseRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(request.getCategory());
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return mapToResponse(savedExpense);
    }

    public List<ExpenseResponse> getAll(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expenseRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ExpenseResponse getById(Long id, Authentication authentication) {

        Expense expense = expenseRepository
                .findByIdAndUserEmail(id, authentication.getName())
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        return mapToResponse(expense);
    }

    public void delete(Long id, Authentication authentication) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        String email = authentication.getName();

        if (!expense.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Access denied");
        }

        expenseRepository.delete(expense);
    }
    public ExpenseResponse update(Long expenseId,
                                 UpdateExpenseRequest request,
                                 String userEmail){
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to update this expense");
        }
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(request.getCategory());

        Expense updatedExpense = expenseRepository.save(expense);

        return mapToResponse(updatedExpense);
    }
    private ExpenseResponse mapToResponse(Expense expense) {
        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .date(expense.getDate())
                .category(expense.getCategory())
                .build();
    }
}