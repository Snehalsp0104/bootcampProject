package com.example.bootcampproject.entity;

import com.example.bootcampproject.enums.TradeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    @Id
    @GeneratedValue
    private int tradeId;

    TradeType tradeType;
    @Positive(message = "Input valid quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stock;

    private float price;

}
