package io.zby.bookstore.auth.roles;

import org.springframework.security.core.GrantedAuthority;

public class AdminAuthority implements GrantedAuthority {

    public AdminAuthority() {
    }

    @Override
    public String getAuthority() {
        return "admin";
    }
}
