package com.portofolio.expensetracker.repository;

import com.portofolio.expensetracker.entity.Expense;
import com.portofolio.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);
    Optional<Expense> findByIdAndUserEmail(Long id, String email);
}
