package io.zby.bookstore.services;


import io.zby.bookstore.dtos.CheckoutDTO;
import io.zby.bookstore.entities.Cart;
import io.zby.bookstore.exceptions.OutOfStockException;

import java.util.Optional;

public interface CartService {
    Optional<Cart> checkout(Cart checkOutCart, CheckoutDTO target) throws OutOfStockException;
}
