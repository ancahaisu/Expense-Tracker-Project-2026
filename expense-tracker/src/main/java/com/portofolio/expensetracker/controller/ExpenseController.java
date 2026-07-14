package com.portofolio.expensetracker.controller;

import com.portofolio.expensetracker.dto.CreateExpenseRequest;
import com.portofolio.expensetracker.dto.ExpenseResponse;
import com.portofolio.expensetracker.dto.UpdateExpenseRequest;
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
    public ExpenseResponse create(
            @RequestBody CreateExpenseRequest request,
            Authentication authentication) {

        return expenseService.create(
                request,
                authentication);
    }

    @GetMapping
    public List<ExpenseResponse> getAll(Authentication authentication) {
        return expenseService.getAll(authentication);
    }
    @GetMapping("/{id}")
    public ExpenseResponse getById(@PathVariable Long id) {
        return expenseService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            Authentication authentication) {

        expenseService.delete(id, authentication);
    }

    @PutMapping("/{id}")
    public ExpenseResponse update(
            @PathVariable Long id,
            @RequestBody UpdateExpenseRequest request,
            Authentication authentication) {

        return expenseService.update(
                id,
                request,
                authentication.getName());
    }
}
