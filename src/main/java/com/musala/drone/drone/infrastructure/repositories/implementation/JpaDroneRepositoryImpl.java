package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import com.musala.drone.drone.infrastructure.entities.DroneEntity;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaDroneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
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
                .map(contententity -> modelMapper.map(contententity, Drone.class))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Drone> GetAllDrones() {
        var result = jpaDroneRepository.findAll().stream()
                .map(content -> modelMapper.map(content, Drone.class))
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
    public boolean ChangeStateDrone(Long droneid, State state)
    {
        var result =  jpaDroneRepository.changeStateDroneById(droneid,state);
        return result > 0;
    }

    @Override
    public Drone FindDroneById(Long droneid) {
        var dronEntity= jpaDroneRepository.findById(droneid);

        if (dronEntity ==null || dronEntity.isEmpty())
            return null;

        var drone = modelMapper.map(dronEntity.get(), Drone.class);
        return drone;
    }

    @Override
    public Drone FindBySerialNumber(String serialNumber)
    {
        var search =  jpaDroneRepository.findBySerialNumber(serialNumber);

        if(search==null)
            return null;

        var result = modelMapper.map(search, Drone.class);
        return result;
    }
}
