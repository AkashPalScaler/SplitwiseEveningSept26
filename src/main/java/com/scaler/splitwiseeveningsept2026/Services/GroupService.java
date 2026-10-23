package com.scaler.splitwiseeveningsept2026.Services;

import com.scaler.splitwiseeveningsept2026.DTO.Transaction;
import com.scaler.splitwiseeveningsept2026.Models.*;
import com.scaler.splitwiseeveningsept2026.Repositories.ExpenseRepository;
import com.scaler.splitwiseeveningsept2026.Repositories.GroupRepository;
import com.scaler.splitwiseeveningsept2026.Repositories.UserExpenseRepository;
import com.scaler.splitwiseeveningsept2026.Strategies.SettleUpStrategy;
import com.scaler.splitwiseeveningsept2026.Strategies.SettleUpStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserExpenseRepository userExpenseRepository;

    public List<Transaction> settleUpGroup(Long groupId) {
        // Fetch the group from the group Id and validate
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()-> new IllegalArgumentException("Group not found")); // group query
        // Fetch all the expenses of the group
//        List<Expense> expenses = group.getExpenses(); // expense query
        List<Expense> expenses = expenseRepository.findAllByGroup(group);
        // Fetch all the userExpenses of the above expenses
        Map<Long, Double> balanceMap = new HashMap<>();
        for(Expense expense : expenses){
            List<UserExpense> userExpenses = userExpenseRepository.findAllByExpense(expense);
            for(UserExpense ue : userExpenses){
                balanceMap.putIfAbsent(ue.getUser().getId(), 0.0);
                if(ue.getType().equals(UserExpenseType.PAID)){
                    balanceMap.put(ue.getUser().getId(), balanceMap.get(ue.getUser().getId()) + ue.getAmount());
                }else{
                    balanceMap.put(ue.getUser().getId(), balanceMap.get(ue.getUser().getId()) - ue.getAmount());
                }
            }
        }
        System.out.println("DEBUG");
        // Loop through all the userExpenses and calculate the balanceMap
        // Get settleUpAlgoStrategy from factory
        SettleUpStrategy strategy = SettleUpStrategyFactory.getStrategy(SettleUpStrategyType.HEAP);
        // List of txns = calling the settleUpStrategy(balanceMap)
        List<Transaction>  transactions = strategy.calculateTransactions(balanceMap);
        return transactions;
    }
}
// Break till - 10:39 PM

// Exp1 : A:+1000 (PAID), B:+1000 (PAID),
//        A:-500 (HAD_TO_PAY), B:-500 (HAD_TO_PAY), C:-500(HAD_TO_PAY), D:-500(HAD_TO_PAY),
// Exp2 : D: +1000 (PAID)
//.       A:-250 (HAD_TO_PAY), B:-250 (HAD_TO_PAY), C:-250(HAD_TO_PAY), D:-250(HAD_TO_PAY),

// Heap Settle Up algorithm :
// 1. create the balance map : A : +250, B : +250, C : -750, D: +250
// 2. Divide people who get paid in one maxheap bucket
//    and people who needs to pay in another min heap bucket
// 3. MaxHeap : A : +250, B : +250, D: +250 | MinHeap : C : -750

// Group -1--M-> Expense -1---M-> userExpenses

// managed and detached entities, persistance-lifesycle, transaction