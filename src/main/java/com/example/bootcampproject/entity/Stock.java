package com.example.bootcampproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @GeneratedValue
    private int stockId;

    private String stockName;

    private float openingPrice;

    private float closingPrice;

    private float highPrice;

    private float lowPrice;

    private float lastPrice;

    private float prevClose;

    //private float lastPrice;


    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trade> tradeList = new ArrayList<>();
}
