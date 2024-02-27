package com.shaikha.floodmanagement.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public AuthToken generateToken(Authentication authentication, String role, boolean remember) {
        Instant now = Instant.now();
        Instant exp = now.plus(remember ? 5 : 1, ChronoUnit.DAYS);

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(exp)
                .subject(authentication.getName())
                .claim("authorities", authorities)
                .claim("role", role)
                .build();

        String jwtToken = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new AuthToken(jwtToken);
    }
}
