package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.Order;
import io.zby.bookstore.entities.projections.InlinedOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = InlinedOrder.class)
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    @RestResource(exported = false)
    @Override
    Iterable<Order> findAll();

    @RestResource(path = "searchByUserId")
    Page<Order> findByCreatedUserId(Pageable page, @Param("userId") Long userId);
}
