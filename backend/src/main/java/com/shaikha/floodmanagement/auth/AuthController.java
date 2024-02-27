package com.shaikha.floodmanagement.auth;

import com.shaikha.floodmanagement.auth.dto.ForgotPasswordPayload;
import com.shaikha.floodmanagement.auth.dto.SignUpPayload;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpPayload payload) {
        boolean success = authService.signUp(payload);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/login/{role}")
    public ResponseEntity<AuthToken> login(Authentication authentication, @RequestParam("remember") boolean remember, @PathVariable("role") String role) {
        Optional<AuthToken> result = authService.login(authentication, role, remember);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(result.get());
    }

    @PostMapping("/password/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid ForgotPasswordPayload payload) {
        boolean success = authService.forgotPassword(payload);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
