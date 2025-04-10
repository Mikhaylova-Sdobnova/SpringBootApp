package ru.mikhailova.julia.SpringBootApp.dao;

import ru.mikhailova.julia.SpringBootApp.model.User;
import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User getUserByUsername(String username);
    void updateUser(User user);
    void deleteById(Long id);
}