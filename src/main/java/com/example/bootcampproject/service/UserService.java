package com.example.bootcampproject.service;

import com.example.bootcampproject.entity.User;
import com.example.bootcampproject.exception.UserNotFoundException;
import com.example.bootcampproject.repository.UserRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
  private UserRepository userRepository;
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    public User getUser(int id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public String getUserName(int id){
        Optional <User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getUserName();
        }
        else throw new UserNotFoundException("user not found");
    }
}
