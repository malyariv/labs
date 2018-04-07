package com.malyariv.library_site.service;

import com.malyariv.library_site.controllers.forms.SuperUser;
import com.malyariv.library_site.entity.User;
import com.malyariv.library_site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;


@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println("username="+user.getUsername()+", role="+user.getRole());
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return new SuperUser(user);
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        if (userDetails instanceof SuperUser) {
            SuperUser user =  (SuperUser) userDetails;
            return userRepository.findByUsername(user.getUsername());
        }
        return null;
    }


    @Override
    public String getRole() {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority a:authorities) {
            return a.getAuthority();
        }
        return null;
    }
}
