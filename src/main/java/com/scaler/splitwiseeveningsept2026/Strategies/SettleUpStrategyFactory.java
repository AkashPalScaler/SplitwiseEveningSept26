package com.scaler.splitwiseeveningsept2026.Strategies;

import com.scaler.splitwiseeveningsept2026.Models.SettleUpStrategyType;
import org.springframework.stereotype.Component;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getStrategy(SettleUpStrategyType type){
        if(type == SettleUpStrategyType.HEAP){
            return new HeapSettleUpStrategy();
        }else{
            throw new IllegalArgumentException("Invalid strategy type");
        }
    }
}
