package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.enums.State;

public interface IChangeStateDroneUseCase {
    public boolean ChangeStateDrone(Long droneid, State state);
}
