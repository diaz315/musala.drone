package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.application.usecase.validation.*;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.ILoadDroneWithMedicationItemsUseCase;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${drone.min.battery.drone.level.to.work}")
    private Integer MinBatteryDroneToWork;

    public LoadDroneWithMedicationItemsUseCaseImpl(
            IDroneRepositoryPort droneRepository,
            IContenRepositoryPort contentRepository, ModelMapper modelMapper)
    {
        this.droneRepository = droneRepository;
        this.contentRepository = contentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean LoadDrone(Long droneId, List<Content> contentList) throws ApplicationException
    {
        DroneContentEmpty.Validate(contentList);

        contentList.forEach(content -> {
            ContentAllowedOnlyLettersNumbers.Validate(content.getName());
            ContentAllowedOnlyUnderscoreAndNumbers.Validate(content.getCode());
            content.setCode(content.getCode().toUpperCase());
        });

        Drone drone = droneRepository.FindDroneById(droneId);
        DroneNotFound.Validate(drone);

        BatteryLevelNotEnought.Validate(drone.getBatteryCapacity() , MinBatteryDroneToWork);
        DroneIsBusy.Validate(drone.getState(),drone.getSerialNumber());

        var totalWeightOfContent = contentList.stream()
                .map(cntList->cntList.getWeight())
                .reduce((double) 0, Double::sum);

        if(drone.getState() == State.LOADING) {
            var currentDroneWeight = contentRepository.GetGenericContentLoadedByDroneId(droneId)
                    .stream()
                    .map(cntList->cntList.getWeight())
                    .reduce((double) 0, Double::sum);

            totalWeightOfContent+= currentDroneWeight;
        }

        MaxDroneCapacity.Validate(drone.getWeightLimit(),totalWeightOfContent);

        //Change Drone status
        drone.setState(drone.getWeightLimit() == totalWeightOfContent? State.LOADED:State.LOADING);
        droneRepository.SaveDrone(drone);

        var tempCotent = contentList.stream().map(contenentity -> modelMapper.map(contenentity, Content.class)).collect(Collectors.toList());

        var result = contentRepository.SaveContent(tempCotent,modelMapper.map(drone, Drone.class));

        return !result.isEmpty();
    }
}
