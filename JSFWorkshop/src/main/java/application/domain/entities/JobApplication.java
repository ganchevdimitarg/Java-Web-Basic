package application.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "job_application")
public class JobApplication extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sector sector;
    @Column(nullable = false)
    private String profession;
    @Column(nullable = false)
    private BigDecimal salary;
    @Column(nullable = false)
    private String description;

    public JobApplication() {
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
