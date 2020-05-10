package io.zby.bookstore.dtos;

import java.util.List;

public class CheckoutDTO {
    private List<Long> items;

    public CheckoutDTO() {
    }

    public CheckoutDTO(List<Long> items) {
        this.items = items;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }
}
