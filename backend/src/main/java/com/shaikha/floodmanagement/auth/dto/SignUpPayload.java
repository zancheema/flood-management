package com.shaikha.floodmanagement.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpPayload {
    private @NotBlank String username;
    private @NotBlank @Email String email;
    private @NotBlank String password;
    private @NotEmpty List<String> roles;
}
