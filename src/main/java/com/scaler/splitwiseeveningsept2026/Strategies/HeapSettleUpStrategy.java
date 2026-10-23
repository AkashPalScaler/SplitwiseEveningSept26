package com.scaler.splitwiseeveningsept2026.Strategies;

import com.scaler.splitwiseeveningsept2026.DTO.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
class Pair implements Comparable<Pair> {
    @Override
    public int compareTo(Pair o) {
        return Double.compare(o.amount, this.amount);
    }

    private Long userId;
    private Double amount;
    public Pair(Long userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
public class HeapSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> calculateTransactions(Map<Long, Double> balanceMap) {
        // 2 buckets - Receivers and Senders
        PriorityQueue<Pair> receivers = new PriorityQueue<>();;
        PriorityQueue<Pair> senders = new PriorityQueue<>();;

        for(Map.Entry<Long, Double> entry : balanceMap.entrySet()){
            if(entry.getValue() >= 0){
                receivers.add(new Pair(entry.getKey(), entry.getValue()));
            }else{
                senders.add(new Pair(entry.getKey(), -1*entry.getValue()));
            }
        }
        List<Transaction> transactions = new ArrayList<>();
        while(!receivers.isEmpty() && !senders.isEmpty()){
            Pair receiver = receivers.poll(); // 900
            Pair sender = senders.poll(); // 1000

            if(receiver.getAmount() > sender.getAmount()){
                Transaction transaction = new Transaction();
                transaction.setFromUserId(sender.getUserId());
                transaction.setToUserId(receiver.getUserId());
                transaction.setAmount(sender.getAmount());
                transactions.add(transaction);

                receivers.add(new Pair(receiver.getUserId(), receiver.getAmount() - sender.getAmount()));
            }else if(receiver.getAmount() < sender.getAmount()){
                Transaction transaction = new Transaction();
                transaction.setFromUserId(sender.getUserId());
                transaction.setToUserId(receiver.getUserId());
                transaction.setAmount(receiver.getAmount());
                transactions.add(transaction);

                senders.add(new Pair(receiver.getUserId(), sender.getAmount() - receiver.getAmount()));
            }else{
                transactions.add(new Transaction(sender.getUserId(), receiver.getUserId(), sender.getAmount()));
            }
        }
        return transactions;
    }
}
