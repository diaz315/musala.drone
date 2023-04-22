package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.dto.ContentDto;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.model.Medication;
import com.musala.drone.drone.domain.ports.in.drone.ILoadDroneWithMedicationItemsUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class LoadDroneWithMedicationItemsUseCaseImpl implements ILoadDroneWithMedicationItemsUseCase {
    private final IDroneRepositoryPort droneRepository;
    private final IContenRepositoryPort contentRepository;
    private final ModelMapper modelMapper;


    public LoadDroneWithMedicationItemsUseCaseImpl(
            IDroneRepositoryPort droneRepository,
            IContenRepositoryPort contentRepository, ModelMapper modelMapper)
    {
        this.droneRepository = droneRepository;
        this.contentRepository = contentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean LoadDrone(Long droneId, List<ContentDto> contentList) {

        Drone drone = droneRepository.FindDroneById(droneId);
        if (drone == null) {
            throw new EntityNotFoundException("Drone not found");
        }

        var tempCotent = contentList.stream().map(contenentity -> modelMapper.map(contenentity, Medication.class)).collect(Collectors.toList());

        tempCotent.forEach(content-> content.setDrone(drone));

        var toDomainModel = tempCotent.stream().map(contenentity -> modelMapper.map(contenentity, Medication.class)).collect(Collectors.toList());

        var result = contentRepository.SaveContent(toDomainModel);

        return !result.isEmpty();
    }
}
