package io.zby.bookstore.entities.projections;

import io.zby.bookstore.entities.Book;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "detailed_book", types = {Book.class})
public interface DetailedBook extends ListedBook {
    String getDescription();
}
