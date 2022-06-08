package kz.ecommerce.controller;

import kz.ecommerce.dto.RegistrationRequest;
import kz.ecommerce.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final AuthenticationMapper authenticationMapper;

    public RegistrationController(AuthenticationMapper authenticationMapper) {
        this.authenticationMapper = authenticationMapper;
    }

    @PostMapping
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult) {
        return ResponseEntity.ok(authenticationMapper.registerUser(registrationRequest.getCaptcha(), registrationRequest, bindingResult));
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateEmailCode(@PathVariable(name = "code") String code) {
        return ResponseEntity.ok(authenticationMapper.activateUser(code));
    }

}
