package application.web.bean;

import application.domain.model.view.HeroViewModel;
import application.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class DetailsBean extends BaseBean {
    private HeroService heroService;
    private ModelMapper modelMapper;

    public DetailsBean() {
    }

    @Inject
    public DetailsBean(HeroService heroService, ModelMapper modelMapper) {
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
