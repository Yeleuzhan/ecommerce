package kz.ecommerce.service;

import kz.ecommerce.domain.User;

import java.util.Optional;

public interface UserService {

    User getUserInfo(String email);

    User updateUserInfo(String email, User user);

}
