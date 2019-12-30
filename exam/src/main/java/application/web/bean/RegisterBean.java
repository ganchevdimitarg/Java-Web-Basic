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
    private RegisterBidingModel registerBidingModel;
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
        this.registerBidingModel = new RegisterBidingModel();
    }

    public void register(){
        if (!this.registerBidingModel.getPassword().equals(this.registerBidingModel.getConfirmPassword())){
            this.redirect("/register");
        }

        this.registerBidingModel.setPassword(DigestUtils.sha256Hex(this.registerBidingModel.getPassword()));
        this.userService.register(this.modelMapper.map(registerBidingModel, UserServiceModel.class));

        this.redirect("/login");
    }

    public RegisterBidingModel getRegisterBidingModel() {
        return registerBidingModel;
    }

    public void setRegisterBidingModel(RegisterBidingModel registerBidingModel) {
        this.registerBidingModel = registerBidingModel;
    }
}
