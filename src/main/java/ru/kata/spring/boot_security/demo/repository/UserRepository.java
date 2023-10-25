package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  {
    Optional<User> findByUsername(String username);
    void save(User user);
    Optional<User> findById(Long aLong);
    List<User> findAll();
    void deleteById(Long aLong);

    void update(User user);
    Optional<User> findByEmail(String email);
}