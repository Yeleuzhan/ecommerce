package kz.ecommerce.service.impl;

import kz.ecommerce.domain.Perfume;
import kz.ecommerce.domain.Review;
import kz.ecommerce.domain.User;
import kz.ecommerce.exception.ApiRequestException;
import kz.ecommerce.repository.PerfumeRepository;
import kz.ecommerce.repository.ReviewRepository;
import kz.ecommerce.repository.UserRepository;
import kz.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PerfumeRepository perfumeRepository;
    private final ReviewRepository reviewRepository;

    public UserServiceImpl(UserRepository userRepository, PerfumeRepository perfumeRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public User getUserInfo(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public User updateUserInfo(String email, User user) {
        User userFromDb = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email not found.", HttpStatus.NOT_FOUND));
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setCity(user.getCity());
        userFromDb.setAddress(user.getAddress());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        userFromDb.setPostIndex(user.getPostIndex());
        return userFromDb;
    }

    @Override
    public List<Perfume> getCart(List<Long> perfumeIds) {
        return perfumeRepository.findByIdIn(perfumeIds);
    }

    @Override
    @Transactional
    public Review addReviewToPerfume(Review review, Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException("Perfume not found", HttpStatus.NOT_FOUND));
        List<Review> reviews = perfume.getReviews();
        reviews.add(review);
        double totalReviews = reviews.size();
        double sumRating = reviews.stream().mapToInt(Review::getRating).sum();
        perfume.setPerfumeRating(sumRating / totalReviews);

        return reviewRepository.save(review);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }
}
