package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.ports.in.drone.ICheckLoadedMedicationItemsForAGivenDroneUseCase;

import java.util.List;

public class CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl implements ICheckLoadedMedicationItemsForAGivenDroneUseCase {

    @Override
    public List<DroneContent> CheckLoadedMedications(Long droneId) {
        return null;
    }
}
