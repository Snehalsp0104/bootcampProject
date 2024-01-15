package com.example.bootcampproject.service;

import com.example.bootcampproject.entity.User;
import com.example.bootcampproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
  private UserRepository userRepository;
    public User createUser(User user)
    {
        return userRepository.save(user);
    }
}
