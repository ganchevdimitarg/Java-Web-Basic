package application.web.beans;

import application.domain.entities.Sector;
import application.domain.models.views.JobViewModel;
import application.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobsListBean extends BaseBean {
    private List<JobViewModel> jobs;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobsListBean() {
    }

    @Inject
    public JobsListBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.setJobs(this.jobApplicationService.getAll().stream()
                .map(j -> this.modelMapper.map(j, JobViewModel.class))
                .collect(Collectors.toList()));
        this.getJobs().forEach(s->s.setSector(s.getSector().toLowerCase()));
    }

    public List<JobViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobViewModel> jobs) {
        this.jobs = jobs;
    }

}
