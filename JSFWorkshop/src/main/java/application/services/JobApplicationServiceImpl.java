package application.services;

import application.domain.entities.JobApplication;
import application.domain.models.services.JobApplicationServiceModel;
import application.repositories.JobAppRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {

   private final JobAppRepository jobAppRepository;
   private final ModelMapper modelMapper;

   @Inject
    public JobApplicationServiceImpl(JobAppRepository jobAppRepository, ModelMapper modelMapper) {
        this.jobAppRepository = jobAppRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(JobApplicationServiceModel job) {
        this.jobAppRepository.save(this.modelMapper.map(job, JobApplication.class));
    }

    @Override
    public List<JobApplicationServiceModel> getAll() {
        return this.jobAppRepository.findAll().stream().map(j-> this.modelMapper.map(j, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobApplicationServiceModel getById(String id) {
        return this.modelMapper.map(this.jobAppRepository.findById(id), JobApplicationServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.jobAppRepository.delete(id);
    }
}
