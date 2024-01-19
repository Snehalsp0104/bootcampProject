package com.example.bootcampproject.dto;

import com.example.bootcampproject.enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeDto {
int userId;
TradeType tradeType;
int quantity;
int stockId;
}
