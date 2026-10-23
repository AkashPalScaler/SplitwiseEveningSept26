package com.scaler.splitwiseeveningsept2026.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExpense extends BaseModel {
    // UserExpense M:1 User
    @ManyToOne
    private User user;
    // UserExpense M:1 expense
    @ManyToOne
    private Expense expense;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType Type;
}
