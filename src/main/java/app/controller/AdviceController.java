package app.controller;

import app.model.User;
import app.service.AuthenticationService;
import app.user.detail.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AdviceController {

    @Autowired
    private AuthenticationService authenticationService;

    @ModelAttribute
    public void setUser(Authentication authentication, Model model) {
        User user = authenticationService.getUserByAuthentication(authentication);
        if (user != null) {
            System.out.println("logg " + user.getId());
        }

        model.addAttribute("user", user);
    }
}