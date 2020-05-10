package io.zby.bookstore.daoimpl;

import io.zby.bookstore.daos.BookDao;
import io.zby.bookstore.entities.Book;
import io.zby.bookstore.repositories.BookRepository;
import io.zby.bookstore.repositories.mongo.BookDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;
    private final BookDescriptionRepository bookDescriptionRepository;

    @Autowired
    public BookDaoImpl(BookRepository bookRepository, BookDescriptionRepository bookDescriptionRepository) {
        this.bookRepository = bookRepository;
        this.bookDescriptionRepository = bookDescriptionRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        var book = this.bookRepository.findById(id);
        var description = this.bookDescriptionRepository.findByBookId(id);
        book.ifPresent(value -> description
                .ifPresentOrElse(bookDescription -> value.setDescription(bookDescription.getContent()),
                        () -> value.setDescription("")));
        return book;
    }
}
