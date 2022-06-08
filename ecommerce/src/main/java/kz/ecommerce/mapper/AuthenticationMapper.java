package kz.ecommerce.mapper;

import kz.ecommerce.domain.User;
import kz.ecommerce.dto.PasswordResetRequest;
import kz.ecommerce.dto.RegistrationRequest;
import kz.ecommerce.dto.auth.AuthenticationRequest;
import kz.ecommerce.dto.auth.AuthenticationResponse;
import kz.ecommerce.dto.user.UserResponse;
import kz.ecommerce.exception.InputFieldException;
import kz.ecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Component
public class AuthenticationMapper {

    private final AuthenticationService authenticationService;
    private final CommonMapper commonMapper;

    public AuthenticationMapper(AuthenticationService authenticationService, CommonMapper commonMapper) {
        this.authenticationService = authenticationService;
        this.commonMapper = commonMapper;
    }

    public String registerUser(String captcha, RegistrationRequest registrationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        User user = commonMapper.convertToEntity(registrationRequest, User.class);
        return authenticationService.registerUser(user, captcha, registrationRequest.getPassword2());
    }

    public String activateUser(String code) {
        return authenticationService.activateUser(code);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Map<String, Object> credentials = authenticationService.login(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setUserResponse(commonMapper.convertToResponse(credentials.get("user"), UserResponse.class));
        authenticationResponse.setToken((String) credentials.get("token"));
        return authenticationResponse;
    }

    public String sendPasswordResetCode(String email) {
        return authenticationService.sendPasswordResetCode(email);
    }

    public String getEmailByPasswordResetCode(String code) {
        return authenticationService.getEmailByPasswordResetCode(code);
    }

    public String resetPassword(String email, PasswordResetRequest passwordResetRequest) {
        return authenticationService.resetPassword(email, passwordResetRequest.getPassword(), passwordResetRequest.getPassword2());
    }

}
