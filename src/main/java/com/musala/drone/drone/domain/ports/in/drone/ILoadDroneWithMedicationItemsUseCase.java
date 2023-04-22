package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.dto.ContentDto;
import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.model.Medication;

import java.util.List;

public interface ILoadDroneWithMedicationItemsUseCase {
    boolean LoadDrone(Long droneId, List<ContentDto> content);
}
