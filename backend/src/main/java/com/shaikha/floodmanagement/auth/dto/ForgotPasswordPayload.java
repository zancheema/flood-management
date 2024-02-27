package com.shaikha.floodmanagement.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordPayload {
    private @NotBlank String username;
    private @NotBlank @Email String email;
    private @NotBlank String password;
}
