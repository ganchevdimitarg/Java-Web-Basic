package application.web.bean;

import application.domain.model.biding.LoginBidingModel;
import application.domain.model.service.UserServiceModel;
import application.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean{
    private UserService userService;
    private LoginBidingModel loginBidingModel;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.loginBidingModel = new LoginBidingModel();
    }

    public void login() {
        UserServiceModel user = this.userService.findByUsernameAndPassword(this.loginBidingModel.getUsername(),
                DigestUtils.sha256Hex(this.loginBidingModel.getPassword()));

        if (user == null){
            this.redirect("/login");
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        sessionMap.put("username", user.getUsername());
        sessionMap.put("userId", user.getId());
        this.redirect("/home");
    }

    public LoginBidingModel getLoginBidingModel() {
        return loginBidingModel;
    }

    public void setLoginBidingModel(LoginBidingModel loginBidingModel) {
        this.loginBidingModel = loginBidingModel;
    }
}
