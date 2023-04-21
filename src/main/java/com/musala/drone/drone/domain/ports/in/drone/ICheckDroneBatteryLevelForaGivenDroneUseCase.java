package com.musala.drone.drone.domain.ports.in.drone;

import java.util.Optional;

public interface ICheckDroneBatteryLevelForaGivenDroneUseCase
{
    Optional<Integer> CheckDroneBattery(Long droneId);
}
