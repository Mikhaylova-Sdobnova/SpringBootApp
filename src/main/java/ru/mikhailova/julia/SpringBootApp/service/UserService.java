package ru.mikhailova.julia.SpringBootApp.service;

import org.springframework.stereotype.Service;
import ru.mikhailova.julia.SpringBootApp.model.Role;
import ru.mikhailova.julia.SpringBootApp.model.User;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User getUserByUsername(String username);
    void updateUser(User user);
    void deleteById(Long id);
}