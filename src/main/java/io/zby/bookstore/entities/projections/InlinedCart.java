package io.zby.bookstore.entities.projections;

import io.zby.bookstore.entities.Cart;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "inlined_cart", types = {Cart.class})
public interface InlinedCart extends BaseEntityProj {
    List<BaseItemProj> getItems();
}
