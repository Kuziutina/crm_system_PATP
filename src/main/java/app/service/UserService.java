package app.service;

import app.dn.UserDTO;
import app.dn.UserRegisterDTO;
import app.form.UserUpdateForm;
import app.model.User;
import app.repository.UserRepository;
import app.service.interfaces.UserServiceInt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void update(long id, UserUpdateForm user) {
        userRepository.update(id, user);
    }

    @Override
    public List<UserDTO> getAllUserDTO() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> users = getAllUsers();
        for(User u: users) {
            userDTOList.add(UserDTO.getByUser(u));
        }
        return userDTOList;
    }


    public User registerNewUserAccount(UserRegisterDTO accountDto) {
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
