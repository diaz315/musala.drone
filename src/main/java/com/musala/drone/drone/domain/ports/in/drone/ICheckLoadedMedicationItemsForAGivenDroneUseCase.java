package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.model.GenericContent;

import java.util.List;

public interface ICheckLoadedMedicationItemsForAGivenDroneUseCase {
    List<GenericContent> CheckLoadedMedications(Long droneId);
}
