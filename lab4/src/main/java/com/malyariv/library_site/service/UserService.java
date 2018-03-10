package com.malyariv.library_site.service;

import com.malyariv.library_site.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User signupUser(User user);

    User getCurrentUser();

    boolean hasRole(String role);

    String getRole();
}
