package io.zby.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.zby.bookstore.constants.UserStatus;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "T_USER")
public class User extends BaseEntity {
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Boolean admin;
    private UserStatus status;
    private Cart cart;
    @JsonIgnore
    private List<Order> orders;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Enumerated(EnumType.STRING)
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @OneToMany(mappedBy = "createdUser", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
