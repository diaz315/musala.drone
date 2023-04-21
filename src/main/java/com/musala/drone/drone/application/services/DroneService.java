package com.musala.drone.drone.application.services;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.ports.in.services.IDroneService;

import java.util.List;
import java.util.Optional;

public class DroneService implements IDroneService {
    @Override
    public Optional<List<Drone>> GetAvailableDrones() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> CheckDroneBattery(Long droneId) {
        return Optional.empty();
    }

    @Override
    public List<GenericContent> CheckLoadedMedications(Long droneId) {
        return null;
    }

    @Override
    public boolean LoadDrone(Long droneId, List<GenericContent> content) {
        return false;
    }

    @Override
    public Drone SaveDrone(Drone drone) {
        return null;
    }
}
