package com.example.bootcampproject.repository;

import com.example.bootcampproject.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Integer> {


    @Query("SELECT t FROM Trade t WHERE t.user.userId = :userId and t.stock.stockId = :stockId and t.tradeType = com.example.bootcampproject.enums.TradeType.Buy")
    public Trade getBuyTrade(@Param("userId") int userId, @Param("stockId") int stockId);

    @Query("SELECT t FROM Trade t WHERE t.user.userId = :userId and t.stock.stockId = :stockId and t.tradeType=com.example.bootcampproject.enums.TradeType.Sell")
    public Trade getSellTrade(@Param("userId") int userId, @Param("stockId") int stockId);

    public List<Trade> getTradeByUserId(int userId);
}
