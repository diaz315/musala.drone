package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.model.Drone;

import java.util.List;

public interface ICheckAvailableDronesForLoadingUseCase {
    List<Drone> GetAvailableDrones();
}
