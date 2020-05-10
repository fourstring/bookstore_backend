package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, Long> {
    @RestResource(exported = false)
    @Override
    Iterable<OrderItem> findAll(Sort sort);

    @RestResource(exported = false)
    @Override
    Page<OrderItem> findAll(Pageable pageable);

    @RestResource(exported = false)
    @Override
    Iterable<OrderItem> findAll();

    @RestResource(path = "searchByOrderId")
    Page<OrderItem> findByOrderId(Pageable pageable, @Param("orderId") Long orderId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Override
    <S extends OrderItem> S save(S s);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Override
    <S extends OrderItem> Iterable<S> saveAll(Iterable<S> iterable);
}
