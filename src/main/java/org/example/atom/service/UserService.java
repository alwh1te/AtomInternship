package org.example.atom.service;

import org.example.atom.model.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);
    boolean deleteUser(int userId);
    List<User> allUsers();
}
