package org.example.atom.controller;

import org.example.atom.model.User;
import org.example.atom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody User user) {
        boolean register = userService.saveUser(user);
        return register
                ? new ResponseEntity<>("User was successfully registered", HttpStatus.OK)
                : new ResponseEntity<>("User already exist", HttpStatus.NOT_MODIFIED);
    }
}
