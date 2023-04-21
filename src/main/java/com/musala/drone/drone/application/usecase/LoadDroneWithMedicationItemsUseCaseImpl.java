package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.ports.in.drone.ILoadDroneWithMedicationItemsUseCase;

import java.util.List;

public class LoadDroneWithMedicationItemsUseCaseImpl implements ILoadDroneWithMedicationItemsUseCase {
    @Override
    public boolean LoadDrone(Long droneId, List<GenericContent> content) {
        return false;
    }
}
