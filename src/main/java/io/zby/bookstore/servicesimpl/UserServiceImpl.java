package io.zby.bookstore.servicesimpl;

import io.zby.bookstore.auth.CustomUserPrincipal;
import io.zby.bookstore.constants.UserStatus;
import io.zby.bookstore.dtos.LoginDTO;
import io.zby.bookstore.dtos.RegisterDTO;
import io.zby.bookstore.entities.Cart;
import io.zby.bookstore.entities.User;
import io.zby.bookstore.exceptions.UserAlreadyExistException;
import io.zby.bookstore.repositories.AuthUserRepository;
import io.zby.bookstore.repositories.CartRepository;
import io.zby.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final CartRepository cartRepository;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, AuthUserRepository authUserRepository, CartRepository cartRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.authUserRepository = authUserRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public User login(LoginDTO loginDTO) {
        var sc = SecurityContextHolder.getContext();
        var authReq = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        var auth = authenticationManager.authenticate(authReq);
        sc.setAuthentication(auth);
        return authUserRepository.findByUsername(loginDTO.getUsername());
    }

    @Override
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    public void register(RegisterDTO registerDTO) throws UserAlreadyExistException {
        if (this.authUserRepository.findByUsername(registerDTO.getUsername()) != null) {
            throw new UserAlreadyExistException();
        } else {
            var user = new User();
            user.setAdmin(false);
            user.setEmail(registerDTO.getEmail());
            user.setUsername(registerDTO.getUsername());
            user.setStatus(UserStatus.active);
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            var cart = new Cart();
            cart.setOwner(user);
            authUserRepository.save(user);
            cartRepository.save(cart);
        }
    }

    @Override
    public User getCurUser() {
        var principal = (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authUserRepository.findByUsername(principal.getUsername());
    }
}
