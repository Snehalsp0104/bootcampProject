package com.example.bootcampproject.controller;

import com.example.bootcampproject.entity.User;
import com.example.bootcampproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("")
    public User createUser(@RequestBody User user)
    {
      return   userService.createUser(user);
    }


}
