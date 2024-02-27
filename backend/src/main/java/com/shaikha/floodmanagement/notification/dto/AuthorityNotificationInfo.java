package com.shaikha.floodmanagement.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityNotificationInfo {
    private long droneId;
    private long reportId;
    private String time;
}
