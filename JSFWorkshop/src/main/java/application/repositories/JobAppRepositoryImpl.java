package application.repositories;

import application.domain.entities.JobApplication;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobAppRepositoryImpl implements JobAppRepository {
    private JobApplication jobApplication;
    private ModelMapper modelMapper;
    private EntityManager em;

    @Inject
    public JobAppRepositoryImpl(ModelMapper modelMapper, EntityManager em) {
        this.modelMapper = modelMapper;
        this.em = em;
    }



    @Override
    public void save(JobApplication jobApplication) {
        this.em.getTransaction().begin();
        this.em.persist(jobApplication);
        this.em.getTransaction().commit();

    }

    @Override
    public List<JobApplication> findAll() {
        this.em.getTransaction().begin();
        List<JobApplication> jobs = this.em.createQuery("select j from JobApplication j", JobApplication.class)
                .getResultList();
        this.em.getTransaction().commit();
        return jobs;
    }

    @Override
    public JobApplication findById(String id) {
        this.em.getTransaction().begin();
        JobApplication job = this.em.createQuery("select j from JobApplication j where j.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();
        this.em.getTransaction().commit();
        return job;
    }

    @Override
    public void delete(String id) {
        this.em.getTransaction().begin();
        this.em.createQuery("delete from JobApplication j where j.id = :id").setParameter("id", id).executeUpdate();
        this.em.getTransaction().commit();
    }
}
