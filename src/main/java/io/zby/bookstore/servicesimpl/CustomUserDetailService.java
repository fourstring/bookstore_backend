package io.zby.bookstore.servicesimpl;

import io.zby.bookstore.auth.CustomUserPrincipal;
import io.zby.bookstore.repositories.AuthUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomUserDetailService(AuthUserRepository authUserRepository, ModelMapper modelMapper) {
        this.authUserRepository = authUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var user = authUserRepository.findByUsername(s);
        if (user != null) {
            return modelMapper.map(user, CustomUserPrincipal.class);
        } else {
            throw new UsernameNotFoundException(s);
        }
    }
}
