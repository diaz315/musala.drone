package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.Medication;
import com.musala.drone.drone.domain.ports.in.drone.ICheckLoadedMedicationItemsForAGivenDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl implements ICheckLoadedMedicationItemsForAGivenDroneUseCase {
    private final IContenRepositoryPort contentRepository;
    private final IDroneRepositoryPort droneRepository;

    public CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl(IContenRepositoryPort contentRepository, IDroneRepositoryPort droneRepository) {
        this.contentRepository = contentRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public List<Medication> CheckLoadedMedications(Long droneId)
    {
        Drone drone = droneRepository.FindDroneById(droneId);
        if (drone == null) {
            throw new EntityNotFoundException("Drone not found");
        }

        var content = contentRepository.GetGenericContentLoadedByDroneId(droneId);

        return content;
    }
}
