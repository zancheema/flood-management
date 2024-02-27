package com.shaikha.floodmanagement.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueTeamNotification {
    private long authorityManagerId;
    private String time;
    private long notificationId;
}
