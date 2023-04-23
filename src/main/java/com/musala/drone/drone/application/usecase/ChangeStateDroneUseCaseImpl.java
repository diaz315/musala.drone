package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.usecase.validation.DroneNotFound;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.ports.in.drone.IChangeStateDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class ChangeStateDroneUseCaseImpl implements IChangeStateDroneUseCase {
    private final IDroneRepositoryPort repository;

    public ChangeStateDroneUseCaseImpl(IDroneRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public boolean ChangeStateDrone(Long droneid, State state)
    {
        var drone= repository.FindDroneById(droneid);
        DroneNotFound.Validate(drone);
        return repository.ChangeStateDrone(droneid,state);
    }
}
