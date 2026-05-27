package com.eduhub.eduhub_backend.controller;


import com.eduhub.eduhub_backend.component.User;
import com.eduhub.eduhub_backend.exceptions.ResourceNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    static List<User> users = new ArrayList<>();

    // Static data - 5 entries
    static {
        users.add(new User("U101", "Harini", "pass101"));
        users.add(new User("U102", "Kavi", "pass102"));
        users.add(new User("U103", "Rahul", "pass103"));
        users.add(new User("U104", "Anu", "pass104"));
        users.add(new User("U105", "Vijay", "pass105"));
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    // Get user using PathVariable
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByPath(@PathVariable String id) {

        // Check special characters
        if (!id.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("User ID contains special characters");
        }

        User user = users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "userId", id));

        return ResponseEntity.ok(user);
    }

    // Get user using RequestParam
    @GetMapping("/search")
    public ResponseEntity<User> getUserByRequestParam(@RequestParam String userId) {

        if (!userId.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("User ID contains special characters");
        }

        User user = users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "userId", userId));

        return ResponseEntity.ok(user);
    }

    // Add new user (6th entry)
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {

        users.add(user);

        return ResponseEntity.ok("New User Added Successfully");
    }

    // Update password
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePassword(
            @PathVariable String id,
            @RequestParam String password) {

        User user = users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "userId", id));

        user.setPassword(password);

        return ResponseEntity.ok("Password Updated Successfully");
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {

        User user = users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "userId", id));

        users.remove(user);

        return ResponseEntity.ok("User Deleted Successfully");
    }
}