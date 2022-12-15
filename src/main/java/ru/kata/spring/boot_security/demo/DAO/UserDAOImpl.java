package ru.kata.spring.boot_security.demo.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    public UserDAOImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("FrOm User", User.class);
        return query.getResultList();
//        return entityManager.createQuery("select distinct u from User u left join fetch u.roles ", User.class)
//                .getResultList();
    }

    @Override
    public User getUser(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(Integer id) {
        entityManager.remove(entityManager.find(User.class, id));

//        entityManager.createQuery(
//                "DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public UserDetails getUser(String username) {

        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.email = :username", User.class).setParameter("username", username)    ///Меняем поле юзер на емэйл для авторизации
                .getSingleResult();
    }

    @Override
    public User getFindByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :id", User.class)
                .setParameter("id", email).getResultList()
                .stream().findAny().orElse(null);

    }
}
