package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.dto.DroneDto;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import com.musala.drone.drone.infrastructure.entities.DroneEntity;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaDroneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<DroneDto> GetAvailableDrones() {
         var query = jpaDroneRepository.findAllByState(State.IDLE);

         var result = query.stream()
                .map(medicationEntity -> modelMapper.map(medicationEntity, DroneDto.class))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public Drone SaveDrone(DroneDto drone) {
        var dronEntity = modelMapper.map(drone, DroneEntity.class);

        var serialDrone = jpaDroneRepository.findBySerialNumber(drone.getSerialNumber());

        if(serialDrone != null)
        {
            throw new DataIntegrityViolationException("Serial number must be unique");
        }

        DroneEntity savedContentEntity = jpaDroneRepository.save(dronEntity);
        return modelMapper.map(savedContentEntity, Drone.class);
    }

    @Override
    public boolean ChangeStateDrone(Long droneid, State state)
    {
        var result =  jpaDroneRepository.changeStateDroneById(droneid,state);
        return result > 0;
    }

    @Override
    public Drone FindDroneById(Long droneid) {
        var dronEntity= jpaDroneRepository.findById(droneid).get();

        if (dronEntity == null) {
            throw new EntityNotFoundException("Drone not found");
        }

        var drone = modelMapper.map(dronEntity, Drone.class);
        return drone;
    }
}
