package com.shaikha.floodmanagement.drone;

import com.shaikha.floodmanagement.location.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drone {
    @Id
    @GeneratedValue
    private long droneId;

    private String droneName;

    private int cameraId;

    private int gpsModule;

    @OneToOne(optional = false)
    private Location location;
}
