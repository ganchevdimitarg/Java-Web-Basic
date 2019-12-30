package application.web.bean;

import application.domain.model.biding.RegisterBidingModel;
import application.domain.model.service.UserServiceModel;
import application.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {
    private RegisterBidingModel user;
    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.user = new RegisterBidingModel();
    }

    public void register(){
        if (!this.user.getPassword().equals(this.user.getConfirmPassword())){
            this.redirect("/register");
        }

        this.user.setPassword(DigestUtils.sha256Hex(this.user.getPassword()));
        this.userService.register(this.modelMapper.map(user, UserServiceModel.class));

        this.redirect("/login");
    }

    public RegisterBidingModel getUser() {
        return user;
    }

    public void setUser(RegisterBidingModel user) {
        this.user = user;
    }
}
