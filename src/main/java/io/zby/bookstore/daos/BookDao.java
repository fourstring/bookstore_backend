package io.zby.bookstore.daos;

import io.zby.bookstore.entities.Book;

import java.util.Optional;

public interface BookDao {
    Optional<Book> findById(Long id);
}
