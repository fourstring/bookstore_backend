package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "cart_items")
public interface CartItemRepository extends PagingAndSortingRepository<CartItem, Long> {
    @RestResource(exported = false)
    @Override
    Iterable<CartItem> findAll(Sort sort);

    @RestResource(exported = false)
    @Override
    Page<CartItem> findAll(Pageable pageable);

    @RestResource(exported = false)
    @Override
    Iterable<CartItem> findAll();

    @RestResource(path = "searchByCartId")
    Iterable<CartItem> findByCartId(@Param("cartId") Long cartId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Override
    <S extends CartItem> S save(S s);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Override
    <S extends CartItem> Iterable<S> saveAll(Iterable<S> iterable);
}
