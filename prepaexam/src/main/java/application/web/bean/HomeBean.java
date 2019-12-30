package application.web.bean;

import application.domain.model.service.UserServiceModel;
import application.domain.model.view.UserViewModel;
import application.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {
    private List<UserViewModel> user;
    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("userId");
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("username");

        this.setUser(this.userService.findAll().stream()
                .filter(u -> !u.getUsername().equals(username) &&
                        !u.getFriends().stream()
                                .map(UserServiceModel::getUsername)
                                .collect(Collectors.toList())
                                .contains(username))
                .map(f -> this.modelMapper.map(f, UserViewModel.class)).collect(Collectors.toList()));
    }

    public void addFriend(String id){
        String userId = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("userId");

        UserServiceModel loggedIn = this.userService.getByID(userId);
        UserServiceModel friend = this.userService.getByID(id);

        loggedIn.getFriends().add(friend);
        friend.getFriends().add(loggedIn);

        this.userService.addFriend(loggedIn);
        this.userService.addFriend(friend);

        redirect("/home");
    }


    public List<UserViewModel> getUser() {
        return user;
    }

    public void setUser(List<UserViewModel> user) {
        this.user = user;
    }


}
