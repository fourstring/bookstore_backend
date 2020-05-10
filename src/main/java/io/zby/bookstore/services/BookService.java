package io.zby.bookstore.services;

import io.zby.bookstore.entities.Book;

import java.util.Optional;

public interface BookService {
    Optional<Book> findOne(Long bookId);

    Optional<Book> updateDescription(Long bookId, String content);
}
