package app.controller;

import app.dn.UserDTO;
import app.form.UserUpdateForm;
import app.model.User;
import app.service.AuthenticationService;
import app.service.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/profile")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserServiceInt userServiceInt;


    @GetMapping
    public String getUser(ModelMap modelMap, Authentication authentication){
        User user = authenticationService.getUserByAuthentication(authentication);
        modelMap.addAttribute("viewUser", user);
        System.out.println("view " + user.getId());
        return "profile";
    }

    @GetMapping(value = "/{id}/change")
    public String changeUser(@PathVariable(name = "id") long id, ModelMap modelMap) {
        User user = userServiceInt.find(id);
        modelMap.addAttribute("viewUser", user);
        return "profile_change";
    }

    @PostMapping(value = "/{id}/change")
    public String changeUserPost(@PathVariable(name = "id") long id,@ModelAttribute(name = "changeUser") UserUpdateForm userUpdateForm) {
        userServiceInt.update(id, userUpdateForm);
        return "redirect: /user/"+id+"";
    }


}
