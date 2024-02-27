package com.shaikha.floodmanagement.notification.dto;

import com.shaikha.floodmanagement.location.dto.LocationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueNotificationReport {
    private long managerId;
    private String name;
    private String subject;
    private String message;
    private String date;
    private String time;
    private LocationInfo location;
    private String filename;
}
