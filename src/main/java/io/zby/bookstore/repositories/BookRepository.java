package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.Book;
import io.zby.bookstore.entities.projections.ListedBook;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = ListedBook.class, path = "books")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    @Override
    @RestResource(exported = false)
    <S extends Book> S save(S s);
}
