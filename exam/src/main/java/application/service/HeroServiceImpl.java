package application.service;

import application.domain.entity.Hero;
import application.domain.model.service.HeroServiceModel;
import application.repository.HeroRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {

    private final ModelMapper modelMapper;
    private final HeroRepository heroRepository;

    @Inject
    public HeroServiceImpl(ModelMapper modelMapper, HeroRepository heroRepository) {
        this.modelMapper = modelMapper;
        this.heroRepository = heroRepository;
    }

    @Override
    public void save(HeroServiceModel hero) {
        this.heroRepository.save(this.modelMapper.map(hero, Hero.class));
    }

    @Override
    public void delete(String id) {
        this.heroRepository.delete(id);
    }

    @Override
    public List<HeroServiceModel> getAll() {
        return this.heroRepository.getAll()
                .stream()
                .map(h-> this.modelMapper.map(h, HeroServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public HeroServiceModel getById(String id) {
        return this.modelMapper.map(this.heroRepository.getById(id), HeroServiceModel.class);
    }
}
