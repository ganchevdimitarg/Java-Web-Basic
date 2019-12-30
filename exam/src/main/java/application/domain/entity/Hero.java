package application.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "hero_class", nullable = false)
    @Enumerated(EnumType.STRING)
    private HeroClass heroClass;
    @Column(name = "level", nullable = false)
    private String level;

    public Hero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
