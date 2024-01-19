package com.example.bootcampproject.service;

import com.example.bootcampproject.entity.Stock;
import com.example.bootcampproject.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StockServiceTest {
    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;


    @BeforeEach
    void setUp() {
        Stock stock = Stock.builder()
                .stockId(6)
                .prevClose(142.15F)
                .lastPrice(139)
                .stockName("AARTECH")
                .closingPrice(140.45F)
                .highPrice(144.55F)
                .lowPrice(139)
                .openingPrice(140.4F)
                .build();

        Mockito.when(stockRepository.findById(6)).thenReturn(Optional.ofNullable(stock));

    }

    @Test
    public void whenValidStock_thenReturnStockDetails() {
        int id = 6;
        Stock stock = Stock.builder()
                .stockId(6)
                .prevClose(142.15F)
                .lastPrice(139)
                .stockName("AARTECH")
                .closingPrice(140.45F)
                .highPrice(144.55F)
                .lowPrice(139)
                .openingPrice(140.4F)
                .build();

        Stock found = stockService.getStockInfo(id);
        assertEquals(found, stock);
    }

}