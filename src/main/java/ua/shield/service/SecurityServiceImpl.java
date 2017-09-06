package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.shield.entity.User;
import ua.shield.exeption.UserIsNotRegisteredExeption;
import ua.shield.service.repository.UserRepository;

/**
 * Created by sa on 01.09.17.
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserRepository userRepository;

    @Override
     public User getRegisteredUser()  {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         if (!(authentication instanceof AnonymousAuthenticationToken)) {
//             return userRepository.findUserByLogin(authentication.getName());
//         } else {
//             throw new UserIsNotRegisteredExeption("User is not registered");
//         }
            return new User(1,"admin");
     }

}
