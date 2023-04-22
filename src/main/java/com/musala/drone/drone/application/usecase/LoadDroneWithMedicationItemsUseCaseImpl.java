package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.model.Medication;
import com.musala.drone.drone.domain.ports.in.drone.ILoadDroneWithMedicationItemsUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IContentDroneRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class LoadDroneWithMedicationItemsUseCaseImpl implements ILoadDroneWithMedicationItemsUseCase {
    private final IContentDroneRepositoryPort contentDroneRepository;
    private final IDroneRepositoryPort droneRepository;
    private final IContenRepositoryPort contentRepository;

    public LoadDroneWithMedicationItemsUseCaseImpl(
            IContentDroneRepositoryPort contentDroneRepository,
            IDroneRepositoryPort droneRepository,
            IContenRepositoryPort contentRepository)
    {
        this.contentDroneRepository = contentDroneRepository;
        this.droneRepository = droneRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public boolean LoadDrone(Long droneId, List<Medication> contentList) {

        Drone drone = droneRepository.FindDroneById(droneId);
        if (drone == null) {
            throw new EntityNotFoundException("Drone not found");
        }

        var ccontentList = contentRepository.SaveContent(contentList);

        contentDroneRepository.LoadContentInDrone(droneId, contentList);

        return false;
    }
}
