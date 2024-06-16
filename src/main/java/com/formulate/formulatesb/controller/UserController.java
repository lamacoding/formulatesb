package com.formulate.formulatesb.controller;

import com.formulate.formulatesb.dto.UserCreateDto;
import com.formulate.formulatesb.model.User;
import com.formulate.formulatesb.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/register")
    public ResponseEntity<User> create(@Valid @RequestBody UserCreateDto userCreateDto) {
        logger.info("User registration: " + userCreateDto.toString());
        User newUser = userService.createUser(userCreateDto);
        return new ResponseEntity<>(newUser, newUser == null ? HttpStatus.CONFLICT : HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
