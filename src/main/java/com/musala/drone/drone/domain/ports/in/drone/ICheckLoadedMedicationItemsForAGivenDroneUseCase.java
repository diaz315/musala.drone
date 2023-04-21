package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.model.DroneContent;

import java.util.List;

public interface ICheckLoadedMedicationItemsForAGivenDroneUseCase {
    List<DroneContent> CheckLoadedMedications(Long droneId);
}
