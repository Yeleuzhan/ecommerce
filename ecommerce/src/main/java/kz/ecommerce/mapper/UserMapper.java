package kz.ecommerce.mapper;

import kz.ecommerce.domain.Review;
import kz.ecommerce.domain.User;
import kz.ecommerce.dto.perfume.FullPerfumeResponse;
import kz.ecommerce.dto.review.ReviewRequest;
import kz.ecommerce.dto.review.ReviewResponse;
import kz.ecommerce.dto.user.BaseUserResponse;
import kz.ecommerce.dto.user.UpdatedUserRequest;
import kz.ecommerce.dto.user.UserResponse;
import kz.ecommerce.exception.ApiRequestException;
import kz.ecommerce.exception.InputFieldException;
import kz.ecommerce.repository.ReviewRepository;
import kz.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {

    private final CommonMapper commonMapper;
    private final UserService userService;

    public UserMapper(CommonMapper commonMapper, UserService userService) {
        this.commonMapper = commonMapper;
        this.userService = userService;
    }

    public UserResponse getUserById(Long userId) {
        return commonMapper.convertToResponse(userService.getUserById(userId), UserResponse.class);
    }

    public UserResponse getUserInfo(String email) {
        User user = new User();
        try {
            user = userService.getUserInfo(email);
        } catch (ApiRequestException exception) {
            exception.printStackTrace();
        }
        return commonMapper.convertToResponse(user, UserResponse.class);
    }

    public UserResponse updateUserInfo(String email, UpdatedUserRequest updatedUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        User user = commonMapper.convertToEntity(updatedUserRequest, User.class);
        return commonMapper.convertToResponse(userService.updateUserInfo(email, user), UserResponse.class);
    }

    public List<FullPerfumeResponse> getCart(List<Long> perfumeIds) {
        return commonMapper.convertToResponseList(userService.getCart(perfumeIds), FullPerfumeResponse.class);
    }

    public ReviewResponse addReviewToPerfume(ReviewRequest reviewRequest, Long perfumeId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        Review review = commonMapper.convertToEntity(reviewRequest, Review.class);
        return commonMapper.convertToResponse(userService.addReviewToPerfume(review, perfumeId), ReviewResponse.class);
    }

    public List<BaseUserResponse> getAllUsers() {
        return commonMapper.convertToResponseList(userService.getAllUsers(), BaseUserResponse.class);
    }

}
