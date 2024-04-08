package org.example.atom.controller;

import org.example.atom.model.User;
import org.example.atom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public List<User> userList() {
        return userService.allUsers();
    }

    @PostMapping("/admin/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") int userId) {
        boolean deleted = userService.deleteUser(userId);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
