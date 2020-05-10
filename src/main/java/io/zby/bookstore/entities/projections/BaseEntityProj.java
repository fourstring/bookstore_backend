package io.zby.bookstore.entities.projections;

import java.util.Date;

public interface BaseEntityProj {
    Long getId();

    Date getCreateAt();

    Date getUpdateAt();
}
