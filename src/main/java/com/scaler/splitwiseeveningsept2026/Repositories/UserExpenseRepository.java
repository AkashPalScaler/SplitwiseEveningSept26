package com.scaler.splitwiseeveningsept2026.Repositories;

import com.scaler.splitwiseeveningsept2026.Models.Expense;
import com.scaler.splitwiseeveningsept2026.Models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    List<UserExpense> findAllByExpense(Expense expense);
}
