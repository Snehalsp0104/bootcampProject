package com.example.bootcampproject.controller;

import com.example.bootcampproject.entity.User;
import com.example.bootcampproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id)
    {
        return userService.getUser(id);
    }

    @GetMapping("/{id}/name")
    public String getUserName(@PathVariable int id){
        return userService.getUserName(id);
    }


}
