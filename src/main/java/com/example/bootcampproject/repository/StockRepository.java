package com.example.bootcampproject.repository;

import com.example.bootcampproject.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {



}
