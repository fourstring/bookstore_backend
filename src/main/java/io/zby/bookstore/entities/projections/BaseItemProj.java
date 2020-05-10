package io.zby.bookstore.entities.projections;

import io.zby.bookstore.entities.CartItem;
import io.zby.bookstore.entities.OrderItem;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlined_item", types = {CartItem.class, OrderItem.class})
public interface BaseItemProj extends BaseEntityProj {
    ListedBook getProduct();

    Long getCount();
}
