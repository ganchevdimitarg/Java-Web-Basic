package application.services;

import application.domain.entities.JobApplication;
import application.domain.models.services.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    void save(JobApplicationServiceModel job);

    List<JobApplicationServiceModel> getAll();

    JobApplicationServiceModel getById(String id);

    void delete(String id);
}
