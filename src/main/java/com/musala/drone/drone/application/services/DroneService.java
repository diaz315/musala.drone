package com.musala.drone.drone.application.services;

import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.*;
import com.musala.drone.drone.domain.ports.in.services.IDroneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DroneService implements IDroneService {
    private final ICheckAvailableDronesForLoadingUseCase checkAvailableDronesForLoadingUseCase;
    private final ICheckDroneBatteryLevelForaGivenDroneUseCase checkDroneBatteryLevelForaGivenDroneUseCase;
    private final ICheckLoadedMedicationItemsForAGivenDroneUseCase checkLoadedMedicationItemsForAGivenDroneUseCase;
    private final ILoadDroneWithMedicationItemsUseCase loadDroneWithMedicationItemsUseCase;
    private final IRegisterdDroneUseCase registerDroneUseCase;
    private final IChangeStateDroneUseCase changeStateDroneUseCase;

    public DroneService(
            ICheckAvailableDronesForLoadingUseCase checkAvailableDronesForLoadingUseCase,
            ICheckDroneBatteryLevelForaGivenDroneUseCase checkDroneBatteryLevelForaGivenDroneUseCase,
            ICheckLoadedMedicationItemsForAGivenDroneUseCase checkLoadedMedicationItemsForAGivenDroneUseCase,
            ILoadDroneWithMedicationItemsUseCase loadDroneWithMedicationItemsUseCase,
            IRegisterdDroneUseCase registerDroneUseCase, IChangeStateDroneUseCase changeStateDroneUseCase)
    {
        this.checkAvailableDronesForLoadingUseCase = checkAvailableDronesForLoadingUseCase;
        this.checkDroneBatteryLevelForaGivenDroneUseCase = checkDroneBatteryLevelForaGivenDroneUseCase;
        this.checkLoadedMedicationItemsForAGivenDroneUseCase = checkLoadedMedicationItemsForAGivenDroneUseCase;
        this.loadDroneWithMedicationItemsUseCase = loadDroneWithMedicationItemsUseCase;
        this.registerDroneUseCase = registerDroneUseCase;
        this.changeStateDroneUseCase = changeStateDroneUseCase;
    }

    @Override
    public List<Drone> GetAvailableDrones()
    {
        return checkAvailableDronesForLoadingUseCase.GetAvailableDrones();
    }

    @Override
    public Optional<Integer> CheckDroneBattery(Long droneId)
    {
        return checkDroneBatteryLevelForaGivenDroneUseCase.CheckDroneBattery(droneId);
    }

    @Override
    public void SetBatteryCharge(Long droneId, Integer battery) {
        checkDroneBatteryLevelForaGivenDroneUseCase.SetBatteryCharge(droneId,battery);
    }

    @Override
    public List<Content> CheckLoadedMedications(Long droneId)
    {
        return checkLoadedMedicationItemsForAGivenDroneUseCase.CheckLoadedMedications(droneId);
    }

    @Override
    public boolean LoadDrone(Long droneId, List<Content> content) throws Exception {
        return loadDroneWithMedicationItemsUseCase.LoadDrone(droneId,content);
    }

    @Override
    public Drone SaveDrone(Drone drone) {

        return registerDroneUseCase.SaveDrone(drone);
    }

    @Override
    public boolean ChangeStateDrone(Long droneid, State state) {
        return changeStateDroneUseCase.ChangeStateDrone(droneid,state);
    }
}
