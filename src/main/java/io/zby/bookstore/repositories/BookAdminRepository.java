package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.Book;
import io.zby.bookstore.entities.projections.ListedBook;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "admin_books", excerptProjection = ListedBook.class)
public interface BookAdminRepository extends PagingAndSortingRepository<Book, Long> {
}
