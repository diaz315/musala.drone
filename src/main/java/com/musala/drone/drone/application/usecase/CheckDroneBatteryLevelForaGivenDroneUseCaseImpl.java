package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.domain.ports.in.drone.ICheckDroneBatteryLevelForaGivenDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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
        var dronEntity= repository.FindDroneById(droneId);

        if (dronEntity == null)
        {
            throw new EntityNotFoundException("Drone not found");
        }

        return Optional.of(dronEntity.getBatteryCapacity());
    }

    @Override
    public void SetBatteryCharge(Long droneId, Integer battery) {
        var drone = repository.FindDroneById(droneId);
        if (drone == null)
            throw new ApplicationException("Drone not found");

        if(drone.getBatteryCapacity()>100)
            throw new ApplicationException("The max battery must be 100");

        if(drone.getBatteryCapacity()<0)
            throw new ApplicationException("The min battery must be 0");

        drone.setBatteryCapacity(battery);
        repository.SaveDrone(drone);
    }

}
