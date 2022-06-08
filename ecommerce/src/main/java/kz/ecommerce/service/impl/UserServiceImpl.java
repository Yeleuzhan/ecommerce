package kz.ecommerce.service.impl;

import kz.ecommerce.domain.User;
import kz.ecommerce.exception.ApiRequestException;
import kz.ecommerce.repository.UserRepository;
import kz.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserInfo(String email) {
        return userRepository.findByEmail(email);
    }
}
