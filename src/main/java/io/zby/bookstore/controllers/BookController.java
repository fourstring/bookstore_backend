package io.zby.bookstore.controllers;

import io.zby.bookstore.dtos.BookDescriptionDTO;
import io.zby.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(path = "/books/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(new EntityModel<>(this.bookService.findOne(bookId)));
    }

    @RequestMapping(path = "/books/{bookId}/update_description", method = RequestMethod.POST)
    public ResponseEntity<?> updateDescription(@PathVariable Long bookId, @RequestBody BookDescriptionDTO bookDescriptionDTO) {
        return ResponseEntity.ok(this.bookService.updateDescription(bookId, bookDescriptionDTO.getContent()));
    }
}
