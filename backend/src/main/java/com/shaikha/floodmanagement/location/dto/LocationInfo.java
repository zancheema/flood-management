package com.shaikha.floodmanagement.location.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo {
    private @NotNull Double lat;
    private @NotNull Double lng;
}
