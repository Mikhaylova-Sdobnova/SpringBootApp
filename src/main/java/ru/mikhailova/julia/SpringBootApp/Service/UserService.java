package ru.mikhailova.julia.SpringBootApp.Service;

import ru.mikhailova.julia.SpringBootApp.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    void updateUser(User user);
    void deleteById(Long id);
}