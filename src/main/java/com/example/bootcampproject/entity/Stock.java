package com.example.bootcampproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private int stockId;

    private String stockName;

    private int openingPrice;

    private int closingPrice;

    private int highPrice;

    private int lowPrice;

    private int lastPrice;


    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trade> tradeList = new ArrayList<>();
}
