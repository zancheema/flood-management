package com.shaikha.floodmanagement.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueNotificationCreationPayload {
    private @NotNull Long reportId;
    private @NotBlank @Email String rescueTeamEmail;
    private @NotBlank String name;
    private @NotBlank String subject;
    private @NotBlank String message;
}
