package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.dto.ContentDto;
import com.musala.drone.drone.domain.model.Medication;

import java.util.List;

public interface ICheckLoadedMedicationItemsForAGivenDroneUseCase {
    List<ContentDto> CheckLoadedMedications(Long droneId);
}
