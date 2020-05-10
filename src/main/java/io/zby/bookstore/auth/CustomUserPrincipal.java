package io.zby.bookstore.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zby.bookstore.auth.roles.AdminAuthority;
import io.zby.bookstore.auth.roles.CustomerAuthority;
import io.zby.bookstore.constants.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserPrincipal implements UserDetails {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Boolean admin;
    private UserStatus status;

    public Long getId() {
        return id;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public UserStatus getStatus() {
        return status;
    }

    public CustomUserPrincipal() {

    }

    public CustomUserPrincipal(Long id, String username, String password, Boolean admin, UserStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.admin) {
            return new ArrayList<>() {
                {
                    add(new AdminAuthority());
                }
            };
        } else {
            return new ArrayList<>() {
                {
                    add(new CustomerAuthority());
                }
            };
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == UserStatus.active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
