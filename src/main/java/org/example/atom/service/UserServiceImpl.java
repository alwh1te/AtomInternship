package org.example.atom.service;

import org.example.atom.model.Role;
import org.example.atom.model.User;
import org.example.atom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isAuthorized(User user) {
        return userRepository.existsById(user.getUserId());
    }

    @Override
    public boolean isAdmin(User user) {
        return userRepository.getReferenceById(user.getUserId()).getRole().equals(Role.ADMIN);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean saveUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            return false;
        }
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
