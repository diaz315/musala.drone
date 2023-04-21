package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Drone;

import java.util.List;
import java.util.Optional;

public interface IDroneRepositoryPort {
    public Optional<List<Drone>> GetAvailableDrones();
    public Drone SaveDrone(Drone drone);
    public boolean ChangeStateDrone(Long droneid, State state);
    public Optional<Drone> FindDroneById(Long droneid);
}