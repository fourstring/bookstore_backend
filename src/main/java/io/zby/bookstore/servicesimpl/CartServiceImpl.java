package io.zby.bookstore.servicesimpl;

import io.zby.bookstore.dtos.CheckoutDTO;
import io.zby.bookstore.entities.Cart;
import io.zby.bookstore.entities.Order;
import io.zby.bookstore.entities.OrderItem;
import io.zby.bookstore.exceptions.OutOfStockException;
import io.zby.bookstore.repositories.*;
import io.zby.bookstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BookRepository bookRepository;
    private final EntityManager entityManager;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, BookRepository bookRepository, EntityManager entityManager) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Optional<Cart> checkout(Cart checkOutCart, CheckoutDTO target) throws OutOfStockException {
        var order = new Order();
        order.setCreatedUser(checkOutCart.getOwner());
        this.orderRepository.save(order);
        for (Long i :
                target.getItems()) {
            var cartItem = this.cartItemRepository.findById(i);
            if (cartItem.isPresent()) {
                var processingItem = cartItem.get();
                var product = processingItem.getProduct();
                var newStock = product.getStock() - processingItem.getCount();
                if (newStock < 0) {
                    throw new OutOfStockException();
                }
                var orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setOrder(order);
                orderItem.setCount(processingItem.getCount());
                this.orderItemRepository.save(orderItem);
                this.cartItemRepository.deleteById(i);
                product.setStock(newStock);
                this.bookRepository.save(product);
            }
        }
        this.entityManager.flush();
        this.entityManager.clear();
        return this.cartRepository.findById(checkOutCart.getId());
    }
}
