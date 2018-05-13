package app.service;

import app.dn.UserRegisterDto;
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

    public User registerNewUserAccount(UserRegisterDto accountDto) {
        User user = User.builder().login(accountDto.getLogin())
                .password(accountDto.getPassword())
                .role("USER_ROLE")
                .build();
        return userRepository.save(user);
    }

    public User getUserByLogin(String username) {
        return userRepository.findOneByLogin(username);
    }
}
