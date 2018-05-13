package app.validation;

import app.dn.UserRegisterDto;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class UserRegistrationValidator implements Validator{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.getName().equals(UserRegisterDto.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("user register validator start validate");
        UserRegisterDto userRegisterDto = (UserRegisterDto) target;

        User sameLogin = userService.getUserByLogin(userRegisterDto.getLogin());

        if (sameLogin != null){
            errors.reject("have_user_with_same_login", "уже есть с таким логином");
        }
        if (userRegisterDto.getLogin() == null || userRegisterDto.getLogin().equals("")) {
            errors.reject("empty_login", "empty login");
        }
        if (userRegisterDto.getPassword() == null || userRegisterDto.getPassword().equals("")) {
            errors.reject("empty_password", "empty password");
        }
        else if (!userRegisterDto.getPassword().equals(userRegisterDto.getMatchingPassword())) {
            errors.reject("passwords_not_equals", "not same password");
            System.out.println(userRegisterDto.getPassword() + "  " + userRegisterDto.getMatchingPassword());
        }

    }
}
