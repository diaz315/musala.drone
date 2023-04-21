package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.ports.in.drone.ICheckLoadedMedicationItemsForAGivenDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IContentDroneRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl implements ICheckLoadedMedicationItemsForAGivenDroneUseCase {
    private final IContenRepositoryPort contentRepository;
    private final IDroneRepositoryPort droneRepository;

    public CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl(IContenRepositoryPort contentRepository, IDroneRepositoryPort droneRepository) {
        this.contentRepository = contentRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public List<GenericContent> CheckLoadedMedications(Long droneId)
    {
        Drone drone = droneRepository.FindDroneById(droneId).get();
        if (drone == null) {
            throw new EntityNotFoundException("Drone not found");
        }

        var content = contentRepository.GetGenericContentLoadedByDroneId(droneId);

        return content;
    }
}
