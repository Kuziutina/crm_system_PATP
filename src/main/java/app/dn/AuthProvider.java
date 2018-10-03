package app.dn;


import app.model.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {

    private UserRepository userDataRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public AuthProvider(UserRepository userDataRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login  = authentication.getName();
        String password = authentication.getCredentials().toString();

        User userData = userDataRepository.findOneByLogin(login);
        if (userData != null) {

            System.out.println(password);
            System.out.println(passwordEncoder.encode(password));
            System.out.println(userData.getPassword());
            System.out.println(passwordEncoder.matches(password, userData.getPassword()));


            if (passwordEncoder.matches(password, userData.getPassword())) {

            } else {
                throw new BadCredentialsException("Invalid login or password");
            }

        } else {
            throw new BadCredentialsException("Invalid login or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        Collection<? extends GrantedAuthority> grantedAuthority = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, password, grantedAuthority);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}