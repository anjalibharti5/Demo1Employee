package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.user;
import com.example.demo.repository.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class simple {
    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/abc/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            Optional<user> userData = UserRepository.findById(id);
            System.out.println(id);
            if (userData.isPresent()) {
                return ResponseEntity.ok(userData.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create/user")

    public ResponseEntity<?> postMethodName(@RequestBody user data) {
        Long id = data.getId();

        Optional<user> existingUser = UserRepository.findById(id);

        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists with ID: " + id);
        } else {

            user savedUser = this.UserRepository.save(data);

            return ResponseEntity.ok(savedUser);
        }
    }

    @PutMapping("/abc/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody user userData) {
		Optional<user> existingUser = UserRepository.findById(id);
		if (existingUser.isPresent()) {
			user updatedUser = existingUser.get();
			updatedUser.setUser_name(userData.getUser_name());
			user updatedUserData = UserRepository.save(updatedUser);
			return ResponseEntity.ok(updatedUserData);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping("delete/abc/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        Optional<user> existingUser = UserRepository.findById(id);
        if (existingUser.isPresent()) {
            UserRepository.deleteById(id);
            return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
