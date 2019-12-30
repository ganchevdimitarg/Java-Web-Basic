package application.repository;

import application.domain.entity.User;
import application.domain.model.service.UserServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
    private EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public User findByID(String id) {
        return this.entityManager.createQuery("select u from User u where u.id =:id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return this.entityManager.createQuery("select u from User u where u.username = :username and u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }


    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public void update(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();
    }


}
