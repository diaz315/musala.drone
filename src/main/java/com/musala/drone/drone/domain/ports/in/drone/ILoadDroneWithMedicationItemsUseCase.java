package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.model.GenericContent;

import java.util.List;

public interface ILoadDroneWithMedicationItemsUseCase {
    boolean LoadDrone(Long droneId, List<GenericContent> content);
}
