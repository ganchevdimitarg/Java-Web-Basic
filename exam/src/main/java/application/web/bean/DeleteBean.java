package application.web.bean;

import application.domain.entity.Hero;
import application.domain.model.service.HeroServiceModel;
import application.domain.model.view.HeroViewModel;
import application.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class DeleteBean extends BaseBean {
    private HeroService heroService;
    private ModelMapper modelMapper;

    public DeleteBean() {
    }

    public DeleteBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    public HeroViewModel getHero(String id){
        return this.modelMapper.map(this.heroService.getById(id), HeroViewModel.class);
    }

    public void delete(){
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.heroService.delete(id);
        this.redirect("/home");
    }
}
