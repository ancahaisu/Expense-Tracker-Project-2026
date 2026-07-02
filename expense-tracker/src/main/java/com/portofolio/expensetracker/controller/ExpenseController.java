package com.portofolio.expensetracker.controller;

import com.portofolio.expensetracker.dto.CreateExpenseRequest;
import com.portofolio.expensetracker.entity.Expense;
import com.portofolio.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public Expense create(
            @RequestBody CreateExpenseRequest request,
            Authentication authentication) {

        return expenseService.create(
                request,
                authentication);
    }

    @GetMapping
    public List<Expense> getAll(Authentication authentication) {
        return expenseService.getAll(authentication);
    }
    @GetMapping("/{id}")
    public Expense getById(@PathVariable Long id) {
        return expenseService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            Authentication authentication) {

        expenseService.delete(id, authentication);
    }

    @PutMapping("/{id}")
    public Expense update(
            @PathVariable Long id,
            @RequestBody CreateExpenseRequest request,
            Authentication authentication) {

        return expenseService.update(
                id,
                request,
                authentication);
    }
}
