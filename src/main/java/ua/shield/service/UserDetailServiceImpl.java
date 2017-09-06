package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.shield.entity.Role;
import ua.shield.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sa on 02.09.17.
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(s);

        Set<GrantedAuthority> setGrantedAuthority = new HashSet();
        setGrantedAuthority.addAll(getSetGrantedAuthority(user.getRoles()));
        if (user.isActive() != true && !user.getRoles().contains(new Role("ROLE_ADMIN"))) {
            throw new UsernameNotFoundException("User is not enable");
        }
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(),
                        user.getPassword(),
                        setGrantedAuthority);
        return userDetails;
    }

    private Set<GrantedAuthority> getSetGrantedAuthority(Set<Role> setRole) {
        Set<GrantedAuthority> grantedAuthoritiesSet = new HashSet<>();
        setRole.forEach(r -> {
            grantedAuthoritiesSet.add(new SimpleGrantedAuthority(r.getRole())
            );
        });
        return grantedAuthoritiesSet;
    }

}
