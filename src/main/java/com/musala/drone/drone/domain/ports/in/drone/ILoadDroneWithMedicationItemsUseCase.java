package com.musala.drone.drone.domain.ports.in.drone;

import com.musala.drone.drone.domain.model.Content;

import java.util.List;

public interface ILoadDroneWithMedicationItemsUseCase {
    boolean LoadDrone(Long droneId, List<Content> content) throws Exception;
}
