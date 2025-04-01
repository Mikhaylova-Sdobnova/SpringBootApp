package ru.mikhailova.julia.SpringBootApp.Service;

import org.springframework.stereotype.Service;
import ru.mikhailova.julia.SpringBootApp.Model.Role;
import ru.mikhailova.julia.SpringBootApp.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface UserService {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User getUserByUsername(String username);
    void updateUser(User user);
    void deleteById(Long id);
    Set<Role> getSetOfRoles(List<String> id);
}