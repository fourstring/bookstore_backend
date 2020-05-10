package io.zby.bookstore.repositories.mongo;

import io.zby.bookstore.documents.BookDescription;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookDescriptionRepository extends CrudRepository<BookDescription, ObjectId> {
    Optional<BookDescription> findByBookId(Long bookId);
}
