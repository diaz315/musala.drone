package com.musala.drone.drone.application.usecase;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.in.drone.IRegisterdDroneUseCase;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class RegisterdDroneUseCaseImpl implements IRegisterdDroneUseCase {
    private final IDroneRepositoryPort repository;

    public RegisterdDroneUseCaseImpl(IDroneRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Drone SaveDrone(Drone drone) {

        if(drone.getSerialNumber().isEmpty())
            throw new ApplicationException("You must enter the serial number");

        if( drone.getSerialNumber().length()>100)
            throw new ApplicationException("The max value of characters is 100");

        if(drone.getBatteryCapacity()>100)
            throw new ApplicationException("The max battery must be 100");

        if(drone.getBatteryCapacity()<0)
            throw new ApplicationException("The min battery must be 0");

        var existingSerialNumber = repository.FindBySerialNumber(drone.getSerialNumber());

        if(drone.getId()==null || drone.getId()==0){

            if(existingSerialNumber != null)
            {
                throw new DataIntegrityViolationException("Serial number must be unique");
            }
        }else {
            var tmpdrone = repository.FindDroneById(drone.getId());

            if(tmpdrone == null)
                throw new ApplicationException("Drone not found");

            if(existingSerialNumber != null &&
                existingSerialNumber.getSerialNumber().equals(drone.getSerialNumber()) &&
                existingSerialNumber.getId() != drone.getId()
            )
            {
                throw new DataIntegrityViolationException("Serial number must be unique");
            }

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
