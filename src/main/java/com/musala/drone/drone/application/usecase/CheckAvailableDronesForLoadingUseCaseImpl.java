package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.ICheckAvailableDronesForLoadingUseCase;

import java.util.List;
import java.util.Optional;

public class CheckAvailableDronesForLoadingUseCaseImpl implements ICheckAvailableDronesForLoadingUseCase {
    @Override
    public Optional<List<Drone>> GetAvailableDrones() {
        return Optional.empty();
    }
}
