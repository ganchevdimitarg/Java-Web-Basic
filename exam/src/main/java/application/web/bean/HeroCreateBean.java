package application.web.bean;

import application.domain.entity.HeroClass;
import application.domain.model.biding.HeroCreateBidingModel;
import application.domain.model.service.HeroServiceModel;
import application.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HeroCreateBean extends BaseBean {
    private HeroCreateBidingModel heroes;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroCreateBean() {
    }

    @Inject
    public HeroCreateBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.heroes = new HeroCreateBidingModel();
    }

    public void create() {
        HeroServiceModel hero = this.modelMapper.map(this.heroes, HeroServiceModel.class);
        HeroClass heroClass = null;

        try {
            heroClass = HeroClass.valueOf(this.heroes.getHeroClass().toUpperCase());
        } catch (Exception e) {
            this.redirect("/create-hero");
        }

        hero.setHeroClass(heroClass);
        this.heroService.save(hero);

        this.redirect("/home");
    }

    public HeroCreateBidingModel getHeroes() {
        return heroes;
    }

    public void setHeroes(HeroCreateBidingModel heroes) {
        this.heroes = heroes;
    }
}
