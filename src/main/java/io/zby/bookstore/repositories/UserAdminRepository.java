package io.zby.bookstore.repositories;

import io.zby.bookstore.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "admin_users")
public interface UserAdminRepository extends PagingAndSortingRepository<User, Long> {
}
