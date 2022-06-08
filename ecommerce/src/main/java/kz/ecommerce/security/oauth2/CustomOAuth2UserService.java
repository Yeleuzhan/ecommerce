package kz.ecommerce.security.oauth2;

import kz.ecommerce.domain.User;
import kz.ecommerce.security.UserPrincipal;
import kz.ecommerce.service.AuthenticationService;
import kz.ecommerce.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public CustomOAuth2UserService(@Lazy AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserFactory.getOAuth2UserInfo(provider, oAuth2User.getAttributes());
        Optional<User> optionalUser = userService.getUserInfo(oAuth2UserInfo.getEmail());

        User user = new User();
        if (optionalUser.isEmpty()) {
            user = authenticationService.registerOAuth2User(provider, oAuth2UserInfo);
        } else {
            user = authenticationService.updateOAuth2User(provider, oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }
}
