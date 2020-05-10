package io.zby.bookstore.entities.projections;

import io.zby.bookstore.constants.UserStatus;

public interface ListedUser extends BaseEntityProj {
    String getUsername();

    String getEmail();

    Boolean getAdmin();

    UserStatus getStatus();
}
