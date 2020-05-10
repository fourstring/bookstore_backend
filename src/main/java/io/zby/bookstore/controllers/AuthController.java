package io.zby.bookstore.controllers;

import io.zby.bookstore.dtos.LoginDTO;
import io.zby.bookstore.dtos.LoginResponseDTO;
import io.zby.bookstore.dtos.RegisterDTO;
import io.zby.bookstore.exceptions.UserAlreadyExistException;
import io.zby.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    LoginResponseDTO login(@RequestBody @Valid LoginDTO loginDTO) {
        return new LoginResponseDTO(this.userService.login(loginDTO), "");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO registerDTO) {
        try {
            this.userService.register(registerDTO);
        } catch (UserAlreadyExistException uaeEx) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public @ResponseBody
    LoginResponseDTO ping() {
        return new LoginResponseDTO(this.userService.getCurUser(), "");
    }

}
