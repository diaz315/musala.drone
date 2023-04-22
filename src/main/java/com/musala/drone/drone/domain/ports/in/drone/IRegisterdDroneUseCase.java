package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.dto.DroneDto;
import com.musala.drone.drone.domain.model.Drone;

public interface IRegisterdDroneUseCase {
    Drone SaveDrone(DroneDto drone);
}
