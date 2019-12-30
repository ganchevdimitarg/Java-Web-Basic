package application.service;

import application.domain.entity.User;
import application.domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel user);

    void addFriend(UserServiceModel userServiceModel);

    void remove(String userId, String friendId);

    UserServiceModel getByID(String id);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    List<UserServiceModel> findAll();


}
