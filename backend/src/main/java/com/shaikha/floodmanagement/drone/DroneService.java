package com.shaikha.floodmanagement.drone;

import com.shaikha.floodmanagement.drone.dto.DroneCreationPayload;
import com.shaikha.floodmanagement.drone.dto.DroneInfo;
import com.shaikha.floodmanagement.location.dto.LocationInfo;

import java.util.List;
import java.util.Optional;

public interface DroneService {
    DroneInfo registerNewDrone(DroneCreationPayload payload);

    Optional<DroneInfo> updateDroneLocation(long droneId, LocationInfo payload);

    List<DroneInfo> getAllDrones();
}
