package ru.mikhailova.julia.SpringBootApp.Service;

import org.springframework.stereotype.Service;
import ru.mikhailova.julia.SpringBootApp.Model.Role;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleByName(String name);

    void saveRole(Role role);

    void deleteRole(Long id);

    void updateRole(Role role);
}