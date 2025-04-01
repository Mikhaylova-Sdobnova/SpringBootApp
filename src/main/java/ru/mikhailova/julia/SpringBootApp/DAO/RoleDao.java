package ru.mikhailova.julia.SpringBootApp.DAO;

import org.springframework.stereotype.Repository;
import ru.mikhailova.julia.SpringBootApp.Model.Role;

import java.util.List;

@Repository
public interface RoleDao {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleByName(String name);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);
}