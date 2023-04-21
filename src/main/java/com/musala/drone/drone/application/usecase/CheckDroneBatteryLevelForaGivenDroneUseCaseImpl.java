package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.ports.in.drone.ICheckDroneBatteryLevelForaGivenDroneUseCase;

import java.util.Optional;

public class CheckDroneBatteryLevelForaGivenDroneUseCaseImpl implements ICheckDroneBatteryLevelForaGivenDroneUseCase {
    @Override
    public Optional<Integer> CheckDroneBattery(Long droneId) {
        return Optional.empty();
    }
}
