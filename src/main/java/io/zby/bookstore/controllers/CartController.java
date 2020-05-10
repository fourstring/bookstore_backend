package io.zby.bookstore.controllers;

import io.zby.bookstore.dtos.CheckoutDTO;
import io.zby.bookstore.exceptions.OutOfStockException;
import io.zby.bookstore.services.CartService;
import io.zby.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;

@RestController
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final EntityManager entityManager;

    @Autowired
    public CartController(CartService cartService, UserService userService, EntityManager entityManager) {
        this.userService = userService;
        this.cartService = cartService;
        this.entityManager = entityManager;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkout")
    public @ResponseBody
    ResponseEntity<?> checkout(@RequestBody CheckoutDTO checkoutDTO) {
        var user = this.userService.getCurUser();
        try {
            var result = cartService.checkout(user.getCart(), checkoutDTO);
            return ResponseEntity.ok(result);
        } catch (OutOfStockException outOfStockException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
