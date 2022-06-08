package kz.ecommerce.dto.auth;

import kz.ecommerce.dto.user.UserResponse;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private UserResponse userResponse;
    private String token;

}
