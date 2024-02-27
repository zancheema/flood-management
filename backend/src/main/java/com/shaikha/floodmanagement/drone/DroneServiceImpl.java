package com.shaikha.floodmanagement.drone;

import com.shaikha.floodmanagement.drone.dto.DroneCreationPayload;
import com.shaikha.floodmanagement.drone.dto.DroneInfo;
import com.shaikha.floodmanagement.location.Location;
import com.shaikha.floodmanagement.location.LocationRepository;
import com.shaikha.floodmanagement.location.dto.LocationInfo;
import com.shaikha.floodmanagement.util.MappingUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final LocationRepository locationRepository;

    public DroneServiceImpl(DroneRepository droneRepository, LocationRepository locationRepository) {
        this.droneRepository = droneRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public DroneInfo registerNewDrone(DroneCreationPayload payload) {
        Location location = locationRepository.save(new Location(0L, payload.getLocation().getLat(), payload.getLocation().getLng()));

        Drone drone = new Drone(0L, payload.getDroneName(), payload.getCameraId(), payload.getGpsModule(), location);
        Drone savedDrone = droneRepository.save(drone);
        return new DroneInfo(
                savedDrone.getDroneId(),
                savedDrone.getDroneName(),
                savedDrone.getCameraId(),
                savedDrone.getGpsModule(),
                new LocationInfo(savedDrone.getLocation().getLat(), savedDrone.getLocation().getLng())
        );
    }

    @Override
    public Optional<DroneInfo> updateDroneLocation(long droneId, LocationInfo payload) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);
        if (optionalDrone.isEmpty()) {
            return Optional.empty();
        }
        Drone drone = optionalDrone.get();
        Location location = drone.getLocation();

        location.setLat(payload.getLat());
        location.setLng(payload.getLng());
        Location savedLocation = locationRepository.save(location);
        drone.setLocation(savedLocation);
        return Optional.of(MappingUtil.getDroneInfo(drone));
    }

    @Override
    public List<DroneInfo> getAllDrones() {
        return droneRepository.findAll()
                .stream()
                .map(MappingUtil::getDroneInfo)
                .collect(Collectors.toList());
    }
}
