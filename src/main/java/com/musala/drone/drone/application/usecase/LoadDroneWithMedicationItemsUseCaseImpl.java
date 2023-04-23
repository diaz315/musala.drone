package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.exception.ApplicationException;
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
    public boolean LoadDrone(Long droneId, List<Content> contentList) throws ApplicationException {

        if(contentList.isEmpty())
            throw new ApplicationException("You must enter at least one content.");

        contentList.forEach(content -> {
            if (!content.getName().matches("^[a-zA-Z0-9_-]*$")) {
                throw new ApplicationException("Allowed only letters, numbers, ‘-‘, ‘_’");
            }

            if (!content.getCode().matches("^[0-9_]+$")) {
                throw new ApplicationException("Allowed only underscore and numbers");
            }

            content.setCode(content.getCode().toUpperCase());
        });

        Drone drone = droneRepository.FindDroneById(droneId);
        if (drone == null)
            throw new ApplicationException("Drone not found");

        if(drone.getBatteryCapacity() < MinBatteryDroneToWork)
            throw new ApplicationException(String.format("the battery level is below %s percent",MinBatteryDroneToWork));

        if(drone.getState() != State.IDLE && drone.getState() != State.LOADING)
            throw new ApplicationException(String.format("the drone with serial %s currently is busy",drone.getSerialNumber()));

        //if(drone.getState() == State.IDLE && drone.getState() == State.LOADING){

        var totalWeightOfContent = contentList.stream()
                .map(cntList->cntList.getWeight())
                .reduce((double) 0, Double::sum);

        if(drone.getState() == State.LOADING) { //-----------------------------
            var currentDroneWeight = contentRepository.GetGenericContentLoadedByDroneId(droneId)
                    .stream()
                    .map(cntList->cntList.getWeight())
                    .reduce((double) 0, Double::sum);

            totalWeightOfContent+= currentDroneWeight;
        }

        if(drone.getWeightLimit()<totalWeightOfContent)
            throw new ApplicationException(String.format("the drone only supports %s grams and the total current content is %s weight",drone.getWeightLimit(),totalWeightOfContent));
        //}

        //Change Drone status
        if(drone.getWeightLimit() == totalWeightOfContent)
            drone.setState(State.LOADED);
        else
            drone.setState(State.LOADING);

        droneRepository.SaveDrone(drone);

        var tempCotent = contentList.stream().map(contenentity -> modelMapper.map(contenentity, Content.class)).collect(Collectors.toList());

        var result = contentRepository.SaveContent(tempCotent,modelMapper.map(drone, Drone.class));

        return !result.isEmpty();
    }
}
