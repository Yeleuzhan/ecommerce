package kz.ecommerce.security.oauth2;

import kz.ecommerce.domain.enums.AuthProvider;
import lombok.SneakyThrows;
import org.apache.tomcat.websocket.AuthenticationException;

import java.util.Map;

public class OAuth2UserFactory {

    @SneakyThrows
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new AuthenticationException("Authentication Exception in OAuth2UserFactory");
        }
    }

}
