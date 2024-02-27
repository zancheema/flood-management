package com.shaikha.floodmanagement.drone.dto;

import com.shaikha.floodmanagement.location.dto.LocationInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneCreationPayload {
    private @NotBlank String droneName;

    private Integer cameraId;

    private Integer gpsModule;
    private @NotNull LocationInfo location;
}
