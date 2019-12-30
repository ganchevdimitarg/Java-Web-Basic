package application.repositories;

import application.domain.entities.User;

public interface UserRepository {

    void save(User user);

    User findByUsernameAndPassword(String username, String password);
}
