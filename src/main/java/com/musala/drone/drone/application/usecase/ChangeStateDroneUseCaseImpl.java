package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.IChangeStateDroneUseCase;

public class ChangeStateDroneUseCaseImpl implements IChangeStateDroneUseCase {
    @Override
    public boolean ChangeStateDrone(Long droneid, State state) {
        return false;
    }
}
