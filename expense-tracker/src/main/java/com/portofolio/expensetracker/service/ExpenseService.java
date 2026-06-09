package com.portofolio.expensetracker.service;

import com.portofolio.expensetracker.dto.CreateExpenseRequest;
import com.portofolio.expensetracker.entity.Expense;
import com.portofolio.expensetracker.entity.User;
import com.portofolio.expensetracker.repository.ExpenseRepository;
import com.portofolio.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    public Expense create(CreateExpenseRequest request) {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(request.getCategory());
        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }
    public Expense getById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }
    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }
    public Expense update(Long id, CreateExpenseRequest request) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(request.getCategory());

        return expenseRepository.save(expense);
    }
}