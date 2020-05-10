package io.zby.bookstore.auth.roles;

import org.springframework.security.core.GrantedAuthority;

public class CustomerAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "customer";
    }
}
