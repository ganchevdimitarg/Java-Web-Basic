package application.repository;

import application.domain.entity.Hero;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {

    private final EntityManager entityManager;

    @Inject
    public HeroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Hero hero) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(hero);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("delete from Hero h where h.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Hero> getAll() {
        this.entityManager.getTransaction().begin();
        List<Hero> heroes = this.entityManager.createQuery("select h from Hero h order by h.level desc", Hero.class).getResultList();
        this.entityManager.getTransaction().commit();
        return heroes;
    }

    @Override
    public Hero getById(String id) {
        this.entityManager.getTransaction().begin();
        Hero hero = this.entityManager.createQuery("select h from Hero h where h.id = :id", Hero.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return hero;
    }
}
