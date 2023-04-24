package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.usecase.validation.DroneNotFound;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.ports.in.drone.IChangeStateDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChangeStateDroneUseCaseImpl implements IChangeStateDroneUseCase {
    private final IDroneRepositoryPort repository;
    private final IContenRepositoryPort  contentRepository;

    @Autowired
    public ChangeStateDroneUseCaseImpl(IDroneRepositoryPort repository, IContenRepositoryPort contentRepository) {
        this.repository = repository;
        this.contentRepository = contentRepository;
    }

    @Override
    public boolean ChangeStateDrone(Long droneid, State state)
    {
        var drone= repository.FindDroneById(droneid);
        DroneNotFound.Validate(drone);
        if(state == State.DELIVERED){
            contentRepository.UpdatedContentDeliveredStatus(droneid);
        }

        return repository.ChangeStateDrone(droneid,state);
    }
}
