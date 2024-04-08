package org.example.atom.security;

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
