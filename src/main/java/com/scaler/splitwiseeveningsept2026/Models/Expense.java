package com.scaler.splitwiseeveningsept2026.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.DocFlavor;
import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel {
    private String description;
    // Expense M:1 createdBy
    @ManyToOne
    private User createdBy;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private ExpenseType type;
    // Expense 1:M UserExpense
    @OneToMany(mappedBy = "expense")
    private List<UserExpense> userExpenses;
    // Expense M:1 Group
    @ManyToOne
    private Group group;
}
// Exp1 : A:+1000 (PAID), B:+1000 (PAID),
//        A:-500 (HAD_TO_PAY), B:-500 (HAD_TO_PAY), C:-500(HAD_TO_PAY), D:-500(HAD_TO_PAY),