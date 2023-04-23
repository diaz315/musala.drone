package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.enums.State;

import java.util.List;

public interface IDroneRepositoryPort {
    public List<Drone> GetAvailableDrones();
    public List<Drone> GetAllDrones();
    public Drone SaveDrone(Drone drone);
    public boolean ChangeStateDrone(Long droneId, State state);
    public Drone FindDroneById(Long droneId);
    public Drone FindBySerialNumber(String serialNumber);
}