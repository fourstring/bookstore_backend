package io.zby.bookstore.entities.projections;

import io.zby.bookstore.entities.Order;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "inlined_order", types = {Order.class})
public interface InlinedOrder extends BaseEntityProj {
    List<BaseItemProj> getItems();

    ListedUser getCreatedUser();
}
