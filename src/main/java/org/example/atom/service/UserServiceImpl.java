//package org.example.atom.service;
//
//import org.example.atom.model.User;
//import org.example.atom.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public User registerUser(User user) {
//        user.setPassword(passwordEncoder().encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
