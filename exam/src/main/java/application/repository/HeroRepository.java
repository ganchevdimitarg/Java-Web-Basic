package application.repository;

import application.domain.entity.Hero;

import java.util.List;

public interface HeroRepository {

    void save(Hero hero);

    void delete(String id);

    List<Hero> getAll();

    Hero getById(String id);
}
