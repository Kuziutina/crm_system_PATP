package app.controller;

import app.form.UserUpdateForm;
import app.model.User;
import app.service.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/crm/user")
public class UserRestController {

    @Autowired
    private UserServiceInt userServiceInt;

    @GetMapping
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userServiceInt.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/{id}/change")
    public String changeUser(@PathVariable(name = "id") long id, ModelMap modelMap) {
        User user = userServiceInt.find(id);
        modelMap.addAttribute("viewUser", user);
        return "change_user";
    }


    @PostMapping(value = "/{id}/delete")
    public String deleteUserPost(@PathVariable(name = "id") long id) {
        userServiceInt.delete(id);
        return "redirect: /user";
    }

}
