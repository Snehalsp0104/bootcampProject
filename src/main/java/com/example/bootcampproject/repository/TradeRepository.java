package com.example.bootcampproject.repository;

import com.example.bootcampproject.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade,Integer> {
}
