package application.web.bean;

import application.domain.model.service.UserServiceModel;
import application.domain.model.view.FriendsViewModel;
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
public class FriendsBean extends BaseBean {
    private List<FriendsViewModel> friends;
    private UserService userService;
    private ModelMapper modelMapper;

    public FriendsBean() {
    }

    @Inject
    public FriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        String id = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("userId");

        this. setFriends(this.userService.getByID(id).getFriends().stream()
        .map(f->this.modelMapper.map(f, FriendsViewModel.class)).collect(Collectors.toList()));
    }

    public void unfriend(String frId){
        String id = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("userId");

        this.userService.remove(id, frId);

        this.redirect("/friends");
    }

    public List<FriendsViewModel> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendsViewModel> friends) {
        this.friends = friends;
    }
}
