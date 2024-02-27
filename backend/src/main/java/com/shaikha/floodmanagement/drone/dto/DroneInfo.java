package com.shaikha.floodmanagement.drone.dto;

import com.shaikha.floodmanagement.location.dto.LocationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneInfo {
    private long droneId;
    private String droneName;
    private int cameraId;
    private int gpsModule;
    private LocationInfo location;
}
