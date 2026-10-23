package com.scaler.splitwiseeveningsept2026.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettleUpGroupResponseDTO {
    List<Transaction> transactions;
    private String message;
    private ResponseStatus status;
}
