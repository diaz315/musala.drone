package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.usecase.validation.DroneNotFound;
import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.ICheckLoadedMedicationItemsForAGivenDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl implements ICheckLoadedMedicationItemsForAGivenDroneUseCase {
    private final IContenRepositoryPort contentRepository;
    private final IDroneRepositoryPort droneRepository;

    public CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl(IContenRepositoryPort contentRepository, IDroneRepositoryPort droneRepository) {
        this.contentRepository = contentRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public List<Content> CheckLoadedMedications(Long droneId)
    {
        Drone drone = droneRepository.FindDroneById(droneId);
        DroneNotFound.Validate(drone);

        return contentRepository.GetGenericContentLoadedByDroneId(droneId);
    }
}
