package com.example.demo.repository;

import com.example.demo.Model.*; 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<user, Long> {
}
