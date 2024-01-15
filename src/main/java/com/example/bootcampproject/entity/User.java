package com.example.bootcampproject.entity;

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
public class User {
    @Id
    @GeneratedValue
    int userId;

    String userName;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Trade> trades = new ArrayList<>();
}
