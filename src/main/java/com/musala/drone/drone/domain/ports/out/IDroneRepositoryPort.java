package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.model.Drone;

import java.util.List;
import java.util.Optional;

public interface IDroneRepositoryPort {
    public Optional<List<Drone>> GetAvailableDrones();
    public Drone CreateDrone(Drone drone);
    public boolean ChangeStateDrone(Long droneid);
    public Optional<Drone> FindDroneById(Long droneid);
}