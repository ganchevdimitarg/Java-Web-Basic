package application.web.beans;

import application.domain.entities.Sector;
import application.domain.models.binding.JobCreateBindingModel;
import application.domain.models.services.JobApplicationServiceModel;
import application.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobCreateBean extends BaseBean{
    private ModelMapper mm;
    private JobCreateBindingModel jobCreateBindingModel;
    private JobApplicationService jobApplicationService;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(ModelMapper mm, JobApplicationService jobApplicationService) {
        this.mm = mm;
        this.jobApplicationService = jobApplicationService;
    }

    @PostConstruct
    public void init(){
        this.jobCreateBindingModel = new JobCreateBindingModel();
    }

    public void create(){
        JobApplicationServiceModel model = this.mm.map(this.jobCreateBindingModel, JobApplicationServiceModel.class);
        Sector sector = null;
        try {
            sector = Sector.valueOf(this.jobCreateBindingModel.getSector().toUpperCase());
        } catch (Exception e){
            this.redirect("/add-job");
        }
        model.setSector(sector);

        this.jobApplicationService.save(model);
        this.redirect("/home");
    }

    public JobCreateBindingModel getJobCreateBindingModel() {
        return jobCreateBindingModel;
    }

    public void setJobCreateBindingModel(JobCreateBindingModel jobCreateBindingModel) {
        this.jobCreateBindingModel = jobCreateBindingModel;
    }
}
