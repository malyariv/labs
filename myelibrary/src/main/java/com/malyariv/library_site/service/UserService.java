package com.malyariv.library_site.service;

import com.malyariv.library_site.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getCurrentUser();

    String getRole();
}
