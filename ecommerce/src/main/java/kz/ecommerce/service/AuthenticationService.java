package kz.ecommerce.service;

import kz.ecommerce.domain.User;
import kz.ecommerce.security.oauth2.OAuth2UserInfo;

import java.util.Map;

public interface AuthenticationService {

    String registerUser(User user, String captcha, String password2);

    User registerOAuth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

    User updateOAuth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

    String activateUser(String code);

    Map<String, Object> login(String email, String password);

    String sendPasswordResetCode(String email);

    String resetPassword(String email, String password, String password2);

    String getEmailByPasswordResetCode(String code);


}
