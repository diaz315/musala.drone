package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.ICheckAvailableDronesForLoadingUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckAvailableDronesForLoadingUseCaseImpl implements ICheckAvailableDronesForLoadingUseCase {
    private final IDroneRepositoryPort repository;

    @Autowired
    public CheckAvailableDronesForLoadingUseCaseImpl(IDroneRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Drone> GetAvailableDrones() {
        return repository.GetAvailableDrones();
    }
}
