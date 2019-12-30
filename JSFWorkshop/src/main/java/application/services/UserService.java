package application.services;

import application.domain.models.services.UserServiceModel;

public interface UserService {

    void save(UserServiceModel user);

    UserServiceModel findUserByUandP(String username, String password);
}
