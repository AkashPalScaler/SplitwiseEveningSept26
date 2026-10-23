package com.scaler.splitwiseeveningsept2026.Strategies;

import com.scaler.splitwiseeveningsept2026.DTO.Transaction;
import com.scaler.splitwiseeveningsept2026.Models.Expense;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {
    public List<Transaction> calculateTransactions(Map<Long, Double> balanceMap);
}
