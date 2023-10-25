package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    User findUserById(Long id);
    void updateUser(User user);
    void saveUser(User user);

    User findByEmail(String email);

    boolean deleteUserById(Long id);
}
