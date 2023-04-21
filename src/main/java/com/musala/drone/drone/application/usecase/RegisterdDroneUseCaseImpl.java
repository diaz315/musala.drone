package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.IRegisterdDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;

public class RegisterdDroneUseCaseImpl implements IRegisterdDroneUseCase {
    private final IDroneRepositoryPort repository;

    public RegisterdDroneUseCaseImpl(IDroneRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Drone SaveDrone(Drone drone) {
        return repository.SaveDrone(drone);
    }
}
