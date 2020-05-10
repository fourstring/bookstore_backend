package io.zby.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "T_CART")
public class Cart extends BaseEntity {
    private List<CartItem> items;
    private User owner;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
