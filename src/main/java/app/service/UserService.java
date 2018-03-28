package app.service;

import app.model.User;
import app.repository.UserRepository;
import app.service.interfaces.UserServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInt {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addNewUser(String login, String password) {
        User user = User.builder().login(login).password(password).build();
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User find(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        User user = find(id);
        userRepository.delete(user);
    }
}
