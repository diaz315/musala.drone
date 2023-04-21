package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.ICheckAvailableDronesForLoadingUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CheckAvailableDronesForLoadingUseCaseImpl implements ICheckAvailableDronesForLoadingUseCase {
    private final IDroneRepositoryPort repository;

    public CheckAvailableDronesForLoadingUseCaseImpl(IDroneRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<Drone>> GetAvailableDrones() {
        return repository.GetAvailableDrones();
    }
}
