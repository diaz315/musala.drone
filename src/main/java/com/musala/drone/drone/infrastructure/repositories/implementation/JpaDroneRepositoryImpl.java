package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import com.musala.drone.drone.infrastructure.entities.DroneEntity;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaDroneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaDroneRepositoryImpl implements IDroneRepositoryPort
{
    private final IJpaDroneRepository jpaDroneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public JpaDroneRepositoryImpl(IJpaDroneRepository jpaDroneRepository, ModelMapper modelMapper) {
        this.jpaDroneRepository = jpaDroneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Drone> GetAvailableDrones() {
         var query = jpaDroneRepository.findAllByState(State.IDLE);

         var result = query.stream()
                .map(medicationEntity -> modelMapper.map(medicationEntity, Drone.class))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Drone SaveDrone(Drone drone) {
        var dronEntity = modelMapper.map(drone, DroneEntity.class);
        DroneEntity savedContentEntity = jpaDroneRepository.save(dronEntity);
        return modelMapper.map(savedContentEntity, Drone.class);
    }

    @Override
    public boolean ChangeStateDrone(Long droneid, State state) {
        return false;
    }

    @Override
    public Drone FindDroneById(Long droneid) {
        var dronEntity= jpaDroneRepository.findById(droneid).get();
        var drone = modelMapper.map(dronEntity, Drone.class);
        return drone;

    }
}
