package kz.ecommerce.service;

import kz.ecommerce.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserInfo(String email);

}
