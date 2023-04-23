package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.usecase.validation.DroneNotFound;
import com.musala.drone.drone.application.usecase.validation.MaxBattery;
import com.musala.drone.drone.application.usecase.validation.MinBattery;
import com.musala.drone.drone.domain.ports.in.drone.ICheckDroneBatteryLevelForaGivenDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CheckDroneBatteryLevelForaGivenDroneUseCaseImpl implements ICheckDroneBatteryLevelForaGivenDroneUseCase {
    private final IDroneRepositoryPort repository;

    public CheckDroneBatteryLevelForaGivenDroneUseCaseImpl(IDroneRepositoryPort repository)
    {
        this.repository = repository;
    }

    @Override
    public Optional<Integer> CheckDroneBattery(Long droneId)
    {
        var drone= repository.FindDroneById(droneId);
        DroneNotFound.Validate(drone);
        return Optional.of(drone.getBatteryCapacity());
    }

    @Override
    public void SetBatteryCharge(Long droneId, Integer battery) {
        var drone = repository.FindDroneById(droneId);

        //Validation
        DroneNotFound.Validate(drone);
        MaxBattery.Validate(drone.getBatteryCapacity());
        MinBattery.Validate(drone.getBatteryCapacity());

        drone.setBatteryCapacity(battery);
        repository.SaveDrone(drone);
    }

}
