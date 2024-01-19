package com.example.bootcampproject.service;

import com.example.bootcampproject.entity.Trade;
import com.example.bootcampproject.entity.User;
import com.example.bootcampproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .userId(52)
                .userName("test")
                .build();

        Mockito.when(userRepository.findById(52)).thenReturn(Optional.ofNullable(user));

    }

    @Test
    public void whenValidUser_ThenShouldReturnUserName() {
        String userName = "test";
        String found = userService.getUserName(52);
        assertEquals(userName, found);
    }

    @Test
    void whenValidUser_ThenShouldReturnUser() {
        int id = 52;
        User user = User.builder()
                .userId(52)
                .userName("test")
                .build();

        User found = userService.getUser(id);
        assertEquals(user, found);

    }
}