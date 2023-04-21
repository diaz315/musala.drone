package com.musala.drone.drone.domain.ports.in.services;

import com.musala.drone.drone.domain.ports.in.drone.*;

public interface IDroneService extends
        ICheckAvailableDronesForLoadingUseCase,
        ICheckDroneBatteryLevelForaGivenDroneUseCase,
        ICheckLoadedMedicationItemsForAGivenDroneUseCase,
        ILoadDroneWithMedicationItemsUseCase,
        IRegisterdDroneUseCase
{
}
