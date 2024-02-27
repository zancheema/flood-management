package com.shaikha.floodmanagement.drone;

import com.shaikha.floodmanagement.drone.dto.DroneCreationPayload;
import com.shaikha.floodmanagement.drone.dto.DroneInfo;
import com.shaikha.floodmanagement.location.dto.LocationInfo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/drones")
public class DroneController {
    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/new")
    public DroneInfo registerNewDrone(@RequestBody @Valid DroneCreationPayload payload) {
        return droneService.registerNewDrone(payload);
    }

    @PutMapping("/update/{droneId}/location")
    public ResponseEntity<DroneInfo> updateDroneLocation(@PathVariable("droneId") long droneId, @RequestBody @Valid LocationInfo payload) {
        Optional<DroneInfo> result = droneService.updateDroneLocation(droneId, payload);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/all")
    public List<DroneInfo> getAllDrones() {
        return droneService.getAllDrones();
    }
}
