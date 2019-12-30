package application.repositories;

import application.domain.entities.JobApplication;

import java.util.List;

public interface JobAppRepository {

    void save(JobApplication jobApplication);

    List<JobApplication> findAll();

    JobApplication findById(String id);

    void delete(String id);
}
