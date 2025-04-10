package ru.mikhailova.julia.SpringBootApp.service;

import org.springframework.stereotype.Service;
import ru.mikhailova.julia.SpringBootApp.model.Role;

import java.util.List;
import java.util.Set;

@Service
public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> getSetOfRoles(List<Long> id);
}