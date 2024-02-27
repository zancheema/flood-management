package com.shaikha.floodmanagement.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
    private final String jwtToken;
}
