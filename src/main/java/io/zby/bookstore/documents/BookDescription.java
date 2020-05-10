package io.zby.bookstore.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BookDescription {
    @Id
    private ObjectId id;

    private String content;

    private Long bookId;

    public BookDescription() {
    }

    public BookDescription(ObjectId id, String content, Long bookId) {
        this.id = id;
        this.content = content;
        this.bookId = bookId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
