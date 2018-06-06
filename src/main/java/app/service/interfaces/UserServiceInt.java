package app.service.interfaces;

import app.dn.UserDTO;
import app.form.UserUpdateForm;
import app.model.User;

import java.util.List;

public interface UserServiceInt {

    void addNewUser(String login, String password);
    List<User> getAllUsers();
    User find(long id);
    void delete(long id);
    void update(long id, UserUpdateForm user);
    List<UserDTO> getAllUserDTO();
}
