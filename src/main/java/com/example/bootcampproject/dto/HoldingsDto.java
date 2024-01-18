package com.example.bootcampproject.dto;

import com.example.bootcampproject.enums.OutCome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoldingsDto {
    String stockName;
    int stockId;
    int quantity;
    float buyPrice;
    float currentPrice;
    OutCome outCome;
    float earning;

}
