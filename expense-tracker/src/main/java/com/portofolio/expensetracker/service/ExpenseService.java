package com.portofolio.expensetracker.service;

import com.portofolio.expensetracker.dto.CreateExpenseRequest;
import com.portofolio.expensetracker.entity.Expense;
import com.portofolio.expensetracker.entity.User;
import com.portofolio.expensetracker.repository.ExpenseRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Expense create(CreateExpenseRequest request) {

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(request.getCategory());

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