package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.Cart;
import io.zby.bookstore.entities.projections.InlinedCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = InlinedCart.class)
public interface CartRepository extends CrudRepository<Cart, Long> {
    @RestResource(exported = false)
    @Override
    Iterable<Cart> findAll();

    @RestResource(path = "searchByOwner", rel = "searchByOwner")
    Cart findByOwnerId(@Param("owner") Long ownerId);
}
