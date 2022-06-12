package kz.ecommerce.configuration;

import kz.ecommerce.security.JwtConfigurer;
import kz.ecommerce.security.oauth2.CustomOAuth2UserService;
import kz.ecommerce.security.oauth2.OAuth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    public WebSecurityConfiguration(JwtConfigurer jwtConfigurer, OAuth2SuccessHandler oAuth2SuccessHandler, CustomOAuth2UserService customOAuth2UserService) {
        this.jwtConfigurer = jwtConfigurer;
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable().authorizeRequests()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**",
                        "/api/v1/registration/**",
                        "/api/v1/perfumes/**",
                        "/api/v1/users/cart",
                        "/api/v1/users/order/**",
                        "/api/v1/users/review",
                        "/websocket", "/websocket/**",
                        "/img/**",
                        "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint().baseUri("/oauth2/authorize")
                .and()
                .redirectionEndpoint().baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2SuccessHandler)
                .and()
                .apply(jwtConfigurer);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
