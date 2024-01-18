package com.example.bootcampproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDto {
List<HoldingsDto> holdings = new ArrayList<>();
int totalPortfolioHolding;
int totalBuyPrice;
int totalProfitLoss;
int totalProfitLossPercentage;
}
