package application.repository;

import application.domain.entity.User;

import java.util.List;

public interface UserRepository {

    void save (User user);

    User findByID(String id);

    User findByUsernameAndPassword(String username, String password);

    void update(User user);
}
