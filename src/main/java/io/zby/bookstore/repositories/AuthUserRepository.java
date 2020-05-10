package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
