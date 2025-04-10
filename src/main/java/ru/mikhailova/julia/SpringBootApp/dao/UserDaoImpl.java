package ru.mikhailova.julia.SpringBootApp.dao;

import org.springframework.stereotype.Repository;
import ru.mikhailova.julia.SpringBootApp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

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
}