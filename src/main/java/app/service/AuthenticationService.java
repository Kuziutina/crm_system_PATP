package app.service;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    UserService userService;

    public User getUserByAuthentication(Authentication authentication) {
        User user = null;
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            user = userService.getUserByLogin(userDetails.getUsername());
        }
        return user;
    }
}
