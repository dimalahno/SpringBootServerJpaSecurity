package myapplication.config.security;

import myapplication.model.User;
import myapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.logging.Logger;

/**
 * Authentication Service - то, что проверяет наличие в базе юзера с введенными данными
 */

@Service
public class AuthenticationService implements UserDetailsService {
    private static Logger log = Logger.getLogger(AuthenticationService.class.getName());

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(userName);
        log.info("Load user by name: " + user.getName());
        if(user == null){
            log.info("User not found!");
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }

}
