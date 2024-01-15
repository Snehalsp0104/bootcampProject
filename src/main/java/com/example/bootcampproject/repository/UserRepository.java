package com.example.bootcampproject.repository;

import com.example.bootcampproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
