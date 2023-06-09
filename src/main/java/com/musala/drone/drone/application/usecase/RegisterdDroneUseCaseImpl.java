package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.usecase.validation.*;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.IRegisterdDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterdDroneUseCaseImpl implements IRegisterdDroneUseCase {
    private final IDroneRepositoryPort repository;

    @Autowired
    public RegisterdDroneUseCaseImpl(IDroneRepositoryPort repository)
    {
        this.repository = repository;
    }

    @Override
    public Drone SaveDrone(Drone drone) {

        DroneCanNotBeNull.Validate(drone);
        EmptySerialNumber.Validate(drone.getSerialNumber());
        MaxSerialNumberLength.Validate(drone.getSerialNumber());
        MaxBattery.Validate(drone.getBatteryCapacity());
        MinBattery.Validate(drone.getBatteryCapacity());

        var existingDrone = repository.FindBySerialNumber(drone.getSerialNumber());

        if(drone.getId()==null || drone.getId()==0){
            SerialNumberUnique.Validate(existingDrone);
        }else {
            var tmpdrone = repository.FindDroneById(drone.getId());

            DroneNotFound.Validate(tmpdrone);
            SerialNumberUnique.Validate(existingDrone,drone);

            tmpdrone.setState(drone.getState());
            tmpdrone.setModel(drone.getModel());
            tmpdrone.setBatteryCapacity(drone.getBatteryCapacity());
            tmpdrone.setWeightLimit(drone.getWeightLimit());
            tmpdrone.setSerialNumber(drone.getSerialNumber());
            drone = tmpdrone;
        }

        drone.setSerialNumber(drone.getSerialNumber().toUpperCase());

        return repository.SaveDrone(drone);
    }
}
