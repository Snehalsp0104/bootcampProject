package com.example.bootcampproject.controller;

import com.example.bootcampproject.entity.Stock;
import com.example.bootcampproject.exception.StockNotFoundException;
import com.example.bootcampproject.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;


    @PostMapping("")
    public void updateStocks(@RequestParam("file") MultipartFile file) throws IOException {
        stockService.updateStocks(file);
    }

    @GetMapping("/{id}")
    public Stock getStock
            (@PathVariable int id) throws StockNotFoundException {
        return stockService.getStockInfo(id);
    }
}
