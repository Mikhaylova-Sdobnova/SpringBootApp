package ru.mikhailova.julia.SpringBootApp.DAO;

import org.springframework.stereotype.Repository;
import ru.mikhailova.julia.SpringBootApp.Model.Role;
import ru.mikhailova.julia.SpringBootApp.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserDao {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User getUserByUsername(String username);
    void updateUser(User user);
    void deleteById(Long id);
    public Set<Role> getSetOfRoles(List<String> rolesId);
}