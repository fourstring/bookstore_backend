package io.zby.bookstore.entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseProductItem extends BaseEntity {
    protected Book product;
    protected Long count;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn
    public Book getProduct() {
        return product;
    }

    public void setProduct(Book product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
