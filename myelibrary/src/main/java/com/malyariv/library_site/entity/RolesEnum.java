package com.malyariv.library_site.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RolesEnum implements GrantedAuthority {
    ROLE_USER {
        @Override
        public String getAuthority() {
            return name();
        }
    }, ROLE_STAFF {
        @Override
        public String getAuthority() {
            return name();
        }
    }
}
