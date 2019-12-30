package application.service;

import application.domain.entity.User;
import application.domain.model.service.UserServiceModel;
import application.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserServiceModel user) {
        this.userRepository.save(this.modelMapper.map(user, User.class));
    }


    @Override
    public UserServiceModel getByID(String id) {
        return this.modelMapper.map(this.userRepository.findByID(id), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.modelMapper.map(this.userRepository.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }
}
