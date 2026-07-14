package com.portofolio.expensetracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateExpenseRequest {
    private String title;

    private BigDecimal amount;

    private LocalDate date;

    private String category;
}
