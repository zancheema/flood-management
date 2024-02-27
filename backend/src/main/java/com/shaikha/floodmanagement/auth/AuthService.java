package com.shaikha.floodmanagement.auth;

import com.shaikha.floodmanagement.auth.dto.ForgotPasswordPayload;
import com.shaikha.floodmanagement.auth.dto.SignUpPayload;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthService {
    boolean signUp(SignUpPayload payload);

    Optional<AuthToken> login(Authentication authentication, String role, boolean remember);

    boolean forgotPassword(ForgotPasswordPayload payload);
}
