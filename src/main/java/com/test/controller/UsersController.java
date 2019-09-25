package com.test.controller;

import com.test.domain.Users;
import com.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);

        return users;
    }

    @PostMapping("/users")
    public String postUser(@RequestBody Users users) {
        System.out.println("Add user with ID = " + users.getId());
        Users _users = usersRepository.save(new Users(users.getId(), users.getPassword()));
        return _users.getId();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        System.out.println("Delete user with ID = " + id + "...");
        try {
            usersRepository.deleteById(id);
        } catch(Exception e) {
            return new ResponseEntity<>("No ID", HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> putUser(@PathVariable("id") String id, @RequestBody Users users) {
        System.out.println("Edit user password with ID = " + id + "...");
        try {
            Optional<Users> usersData = usersRepository.findById(id);
            if(usersData.isPresent()) {
                Users _users = usersData.get();
                _users.setPassword(users.getPassword());
                usersRepository.save(_users);
                return new ResponseEntity<>("DONE", HttpStatus.OK);
            } else {
                throw new Exception(new Error("No ID"));
            }
        } catch(Exception e) {
            return new ResponseEntity<>("No ID", HttpStatus.OK);
        }
    }
}
