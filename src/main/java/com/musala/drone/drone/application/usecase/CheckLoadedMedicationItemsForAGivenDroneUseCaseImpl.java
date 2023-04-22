package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.dto.ContentDto;
import com.musala.drone.drone.domain.dto.DroneContentDto;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.ICheckLoadedMedicationItemsForAGivenDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl implements ICheckLoadedMedicationItemsForAGivenDroneUseCase {
    private final IContenRepositoryPort contentRepository;
    private final IDroneRepositoryPort droneRepository;
    private final ModelMapper modelMapper;

    public CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl(IContenRepositoryPort contentRepository, IDroneRepositoryPort droneRepository, ModelMapper modelMapper) {
        this.contentRepository = contentRepository;
        this.droneRepository = droneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContentDto> CheckLoadedMedications(Long droneId)
    {
        Drone drone = droneRepository.FindDroneById(droneId);
        if (drone == null) {
            throw new EntityNotFoundException("Drone not found");
        }

        var content = contentRepository.GetGenericContentLoadedByDroneId(droneId);


        return content;
    }
}
