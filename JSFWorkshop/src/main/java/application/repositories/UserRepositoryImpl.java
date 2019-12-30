package application.repositories;

import application.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    @Inject
    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(User user) {
        this.em.getTransaction().begin();
        this.em.persist(user);
        this.em.getTransaction().commit();

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        this.em.getTransaction().begin();
        User user = this.em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        this.em.getTransaction().commit();
        return user;
    }
}
