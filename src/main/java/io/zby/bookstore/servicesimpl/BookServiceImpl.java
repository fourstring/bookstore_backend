package io.zby.bookstore.servicesimpl;

import io.zby.bookstore.daos.BookDao;
import io.zby.bookstore.documents.BookDescription;
import io.zby.bookstore.entities.Book;
import io.zby.bookstore.repositories.BookRepository;
import io.zby.bookstore.repositories.mongo.BookDescriptionRepository;
import io.zby.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookRepository bookRepository;
    private final BookDescriptionRepository bookDescriptionRepository;

    @Autowired
    public BookServiceImpl(BookDao bookDao, BookRepository bookRepository, BookDescriptionRepository bookDescriptionRepository) {
        this.bookDao = bookDao;
        this.bookRepository = bookRepository;
        this.bookDescriptionRepository = bookDescriptionRepository;
    }

    @Override
    public Optional<Book> findOne(Long bookId) {
        return this.bookDao.findById(bookId);
    }

    @Override
    public Optional<Book> updateDescription(Long bookId, String content) {
        var book = this.bookDao.findById(bookId);
        book.ifPresent(value ->
                this.bookDescriptionRepository.findByBookId(value.getId()).ifPresentOrElse(bookDescription -> {
                    bookDescription.setContent(content);
                    this.bookDescriptionRepository.save(bookDescription);
                    value.setDescription(content);
                }, () -> {
                    var description = new BookDescription();
                    description.setBookId(value.getId());
                    description.setContent(content);
                    this.bookDescriptionRepository.save(description);
                    value.setDescription(content);
                }));
        return book;
    }
}
