package com.scaler.splitwiseeveningsept2026.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Long fromUserId;
    private Long toUserId;
    private Double amount;
}

// A should pay 100 to B
// B should pay 200 to D
