package io.zby.bookstore.services;

import io.zby.bookstore.dtos.LoginDTO;
import io.zby.bookstore.dtos.RegisterDTO;
import io.zby.bookstore.entities.User;
import io.zby.bookstore.exceptions.UserAlreadyExistException;

public interface UserService {
    User login(LoginDTO loginDTO);

    void register(RegisterDTO registerDTO) throws UserAlreadyExistException;

    User getCurUser();
}
