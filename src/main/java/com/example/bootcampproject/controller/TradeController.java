package com.example.bootcampproject.controller;

import com.example.bootcampproject.dto.PortfolioDto;
import com.example.bootcampproject.dto.TradeDto;
import com.example.bootcampproject.entity.Trade;
import com.example.bootcampproject.exception.*;
import com.example.bootcampproject.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;
    @PostMapping("")
    public void addTrade(@RequestBody TradeDto tradeDto) throws InvalidQuantityException, InvalidSellException, InvalidTradeTypeException, StockNotFoundException, UserNotFoundException
    {
        tradeService.addTrade(tradeDto);
    }

    @GetMapping("/portfolio/{userId}")
    public PortfolioDto portfolioDetails(@PathVariable int userId)
    {
      return tradeService.portfolioDetails(userId);
    }

}
