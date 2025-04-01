package ru.mikhailova.julia.SpringBootApp.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.julia.SpringBootApp.Model.Role;
import ru.mikhailova.julia.SpringBootApp.Model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ru.mikhailova.julia.SpringBootApp.Service.RoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    private final RoleService roleService;
    private final Role role;

    @Autowired
    public UserDaoImpl(RoleService roleService, Role role){
        this.roleService = roleService;
        this.role = role;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select distinct a from User a left join fetch a.roles", User.class)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.createQuery("select distinct a from User a left join fetch a.roles where a.id = :id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select distinct a from User a left join fetch a.roles where a.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }

    @Override
    public void updateUser(User user){
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        User userById = findById(id);
        entityManager.remove(userById);
    }

    @Override
    public Set<Role> getSetOfRoles(List<String> roles){
        Set<Role> roleSet = new HashSet<>();
        for (String name: roles) {
            roleSet.add(new Role(name));
        }
        for (Role role : roleSet) {
            roleService.saveRole(role);
        }

        return roleSet;
    }
}