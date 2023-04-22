package com.musala.drone.drone.application.services;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.model.Medication;
import com.musala.drone.drone.domain.ports.in.drone.*;
import com.musala.drone.drone.domain.ports.in.services.IDroneService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DroneService implements IDroneService {
    private final ICheckAvailableDronesForLoadingUseCase checkAvailableDronesForLoadingUseCase;
    private final ICheckDroneBatteryLevelForaGivenDroneUseCase checkDroneBatteryLevelForaGivenDroneUseCase;
    private final ICheckLoadedMedicationItemsForAGivenDroneUseCase checkLoadedMedicationItemsForAGivenDroneUseCase;
    private final ILoadDroneWithMedicationItemsUseCase loadDroneWithMedicationItemsUseCase;
    private final IRegisterdDroneUseCase registerdDroneUseCase;

    public DroneService(ICheckAvailableDronesForLoadingUseCase checkAvailableDronesForLoadingUseCase, ICheckDroneBatteryLevelForaGivenDroneUseCase checkDroneBatteryLevelForaGivenDroneUseCase, ICheckLoadedMedicationItemsForAGivenDroneUseCase checkLoadedMedicationItemsForAGivenDroneUseCase, ILoadDroneWithMedicationItemsUseCase loadDroneWithMedicationItemsUseCase, IRegisterdDroneUseCase registerdDroneUseCase) {
        this.checkAvailableDronesForLoadingUseCase = checkAvailableDronesForLoadingUseCase;
        this.checkDroneBatteryLevelForaGivenDroneUseCase = checkDroneBatteryLevelForaGivenDroneUseCase;
        this.checkLoadedMedicationItemsForAGivenDroneUseCase = checkLoadedMedicationItemsForAGivenDroneUseCase;
        this.loadDroneWithMedicationItemsUseCase = loadDroneWithMedicationItemsUseCase;
        this.registerdDroneUseCase = registerdDroneUseCase;
    }

    @Override
    public List<Drone> GetAvailableDrones() {
        return checkAvailableDronesForLoadingUseCase.GetAvailableDrones();
    }

    @Override
    public Optional<Integer> CheckDroneBattery(Long droneId) {
        return checkDroneBatteryLevelForaGivenDroneUseCase.CheckDroneBattery(droneId);
    }

    @Override
    public List<Medication> CheckLoadedMedications(Long droneId) {
        return checkLoadedMedicationItemsForAGivenDroneUseCase.CheckLoadedMedications(droneId);
    }

    @Override
    public boolean LoadDrone(Long droneId, List<Medication> content) {
        return loadDroneWithMedicationItemsUseCase.LoadDrone(droneId,content);
    }

    @Override
    public Drone SaveDrone(Drone drone) {
        return registerdDroneUseCase.SaveDrone(drone);
    }
}
