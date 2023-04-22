package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.dto.DroneDto;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.Medication;

import java.util.List;
import java.util.Optional;

public interface IDroneRepositoryPort {
    public List<DroneDto> GetAvailableDrones();
    public Drone SaveDrone(DroneDto drone);
    public boolean ChangeStateDrone(Long droneid, State state);
    public Drone FindDroneById(Long droneid);
}