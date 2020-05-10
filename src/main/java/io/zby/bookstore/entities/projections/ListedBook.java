package io.zby.bookstore.entities.projections;

import io.zby.bookstore.entities.Book;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listed_book", types = {Book.class})
public interface ListedBook extends BaseEntityProj {
    String getTitle();

    String getAuthor();

    String getIsbn();

    String getCover_pic();

    Double getPrice();

    Long getStock();
}
