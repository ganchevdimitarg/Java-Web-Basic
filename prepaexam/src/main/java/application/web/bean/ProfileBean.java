package application.web.bean;

import application.domain.model.view.ProfileVM;
import application.domain.model.view.UserViewModel;
import application.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class ProfileBean {
    private ModelMapper modelMapper;
    private UserService userService;
    private UserViewModel user;

    public ProfileBean() {
    }

    public ProfileBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        String id = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
                .getRequest()).getParameter("id");

        this.user = this.modelMapper.map(this.userService.getByID(id), UserViewModel.class);
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}
