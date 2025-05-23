package ru.mikhailova.julia.SpringBootApp.service;

import org.springframework.stereotype.Service;
import ru.mikhailova.julia.SpringBootApp.model.User;

import java.util.List;

@Service
public interface UserService {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User getUserByUsername(String username);
    boolean updateUser(User user);
    boolean deleteById(Long id);
}