package app.service.interfaces;

import app.model.User;

import java.util.List;

public interface UserServiceInt {

    void addNewUser(String login, String password);
    List<User> getAllUsers();
    User find(long id);
    void delete(long id);
}
