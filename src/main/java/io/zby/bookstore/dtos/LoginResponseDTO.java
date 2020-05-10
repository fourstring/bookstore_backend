package io.zby.bookstore.dtos;

import io.zby.bookstore.entities.User;

public class LoginResponseDTO {
    private User user;
    private String csrfToken;

    public LoginResponseDTO(User user, String csrfToken) {
        this.user = user;
        this.csrfToken = csrfToken;
    }

    public LoginResponseDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }
}
