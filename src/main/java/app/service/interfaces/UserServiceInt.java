package app.service.interfaces;

import app.dto.UserDTO;
import app.form.UserImageForm;
import app.form.UserUpdateForm;
import app.model.Ticket;
import app.model.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserServiceInt {

    void addNewUser(String login, String password);
    void addUser(UserDTO userDTO);
    void addUser(User user);
    List<User> getAllUsers();
    User find(long id);
    User find(User user);
    void delete(long id);
    void updateUserData(User user, UserUpdateForm userUpdateForm);
    void updateUserImage(User user, UserImageForm userImageForm);
    void loadUserImage(long id, HttpServletResponse response);
    List<UserDTO> getAllUserDTO();
    User createUserFromUserDTO(UserDTO userDTO);
}
