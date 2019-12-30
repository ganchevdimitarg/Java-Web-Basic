package application.service;

import application.domain.entity.Hero;
import application.domain.model.service.HeroServiceModel;

import java.util.List;

public interface HeroService {

    void save(HeroServiceModel hero);

    void delete(String id);

    List<HeroServiceModel> getAll();

    HeroServiceModel getById(String id);
}
