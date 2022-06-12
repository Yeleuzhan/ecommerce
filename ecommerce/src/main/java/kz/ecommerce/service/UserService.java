package kz.ecommerce.service;

import kz.ecommerce.domain.Perfume;
import kz.ecommerce.domain.Review;
import kz.ecommerce.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserById(Long userId);

    User getUserInfo(String email);

    List<User> getAllUsers();

    User updateUserInfo(String email, User user);

    List<Perfume> getCart(List<Long> perfumeIds);

    Review addReviewToPerfume(Review review, Long perfumeId);

}
