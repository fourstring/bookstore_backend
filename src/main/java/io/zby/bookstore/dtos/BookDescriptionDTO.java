package io.zby.bookstore.dtos;

public class BookDescriptionDTO {
    private String content;

    public BookDescriptionDTO() {
    }

    public BookDescriptionDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
