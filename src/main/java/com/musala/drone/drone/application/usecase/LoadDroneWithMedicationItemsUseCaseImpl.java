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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadDroneWithMedicationItemsUseCaseImpl implements ILoadDroneWithMedicationItemsUseCase {
    private final IDroneRepositoryPort droneRepository;
    private final IContenRepositoryPort contentRepository;
    private final ModelMapper modelMapper;

    public Double TotalWeightOfContent;

    @Value("${drone.min.battery.drone.level.to.work}")
    public Integer MinBatteryDroneToWork;

    @Autowired
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
        Drone drone = droneRepository.FindDroneById(droneId);
        DroneNotFound.Validate(drone);

        DroneContentEmpty.Validate(contentList);

        contentList.forEach(content -> {
            EmptyContentType.Validate(content.getType());
            ContentAllowedOnlyLettersNumbers.Validate(content.getName());
            ContentAllowedOnlyUnderscoreAndNumbers.Validate(content.getCode());
            content.setCode(content.getCode().toUpperCase());
        });

        BatteryLevelNotEnought.Validate(drone.getBatteryCapacity() , MinBatteryDroneToWork);
        DroneIsBusy.Validate(drone.getState(),drone.getSerialNumber());

        TotalWeightOfContent = contentList.stream()
                .map(cntList->cntList.getWeight())
                .reduce((double) 0, Double::sum);

        if(drone.getState() == State.LOADING) {
            var currentDroneWeight = contentRepository.GetGenericContentLoadedByDroneId(droneId)
                    .stream()
                    .map(cntList->cntList.getWeight())
                    .reduce((double) 0, Double::sum);

            TotalWeightOfContent+= currentDroneWeight;
        }

        MaxDroneCapacity.Validate(drone.getWeightLimit(),TotalWeightOfContent);

        //Change Drone status
        drone.setState(drone.getWeightLimit() == TotalWeightOfContent? State.LOADED:State.LOADING);
        droneRepository.SaveDrone(drone);

        var tempCotent = contentList.stream().map(contenentity -> modelMapper.map(contenentity, Content.class)).collect(Collectors.toList());

        var result = contentRepository.SaveContent(tempCotent,modelMapper.map(drone, Drone.class));

        return result!=null && !result.isEmpty();
    }
}
