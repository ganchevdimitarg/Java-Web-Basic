package application.web.beans;

import application.domain.models.binding.UserRegisterBinding;
import application.domain.models.services.UserServiceModel;
import application.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {
    private UserRegisterBinding user;
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
        this.user = new UserRegisterBinding();
    }

    public void register() {

        if (!this.user.getPassword().equals(this.user.getConfirmpassword())){
            return;
        }
        this.user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.userService.save(this.modelMapper.map(user, UserServiceModel.class));

        this.redirect("/login");

    }

    public UserRegisterBinding getUser() {
        return user;
    }

    public void setUser(UserRegisterBinding user) {
        this.user = user;
    }
}
