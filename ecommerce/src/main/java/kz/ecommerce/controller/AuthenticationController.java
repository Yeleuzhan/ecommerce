package kz.ecommerce.controller;

import kz.ecommerce.dto.PasswordResetRequest;
import kz.ecommerce.dto.auth.AuthenticationRequest;
import kz.ecommerce.dto.auth.AuthenticationResponse;
import kz.ecommerce.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationMapper authenticationMapper;

    public AuthenticationController(AuthenticationMapper authenticationMapper) {
        this.authenticationMapper = authenticationMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationMapper.login(authenticationRequest));
    }

    @GetMapping("/forgot/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(authenticationMapper.sendPasswordResetCode(email));
    }

    @GetMapping("/reset/{code}")
    public ResponseEntity<String> resetPassword(@PathVariable(name = "code") String code) {
        return ResponseEntity.ok(authenticationMapper.getEmailByPasswordResetCode(code));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        return ResponseEntity.ok(authenticationMapper.resetPassword(passwordResetRequest.getEmail(), passwordResetRequest));
    }

    @GetMapping("/success")
    public String success() {
        return "ok";
    }

}
