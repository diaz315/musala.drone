package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.ports.in.drone.ICheckDroneBatteryLevelForaGivenDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CheckDroneBatteryLevelForaGivenDroneUseCaseImpl implements ICheckDroneBatteryLevelForaGivenDroneUseCase {
    private final IDroneRepositoryPort repository;

    public CheckDroneBatteryLevelForaGivenDroneUseCaseImpl(IDroneRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Integer> CheckDroneBattery(Long droneId)
    {
        return Optional.of(repository.FindDroneById(droneId).getBatteryCapacity());
    }
}
