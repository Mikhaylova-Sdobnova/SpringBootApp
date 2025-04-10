package ru.mikhailova.julia.SpringBootApp.dao;

import ru.mikhailova.julia.SpringBootApp.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Set<Role> getSetOfRoles(List<Long> rolesId);
}