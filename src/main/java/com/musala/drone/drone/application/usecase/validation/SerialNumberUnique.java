package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.domain.model.Drone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;

public class SerialNumberUnique {
    @Value("${drone.validation.msg.serialnumberunique}")
    private static String ErrorMsg;

    public static void Validate(Drone drone){
        if(drone != null)
        {
            Error();
        }
    }

    public static void Validate(Drone existingDrone,Drone currentDrone){
        if(existingDrone != null &&
                existingDrone.getSerialNumber().equals(currentDrone.getSerialNumber()) &&
                existingDrone.getId() != currentDrone.getId()
        )
        {
            SerialNumberUnique.Error();
        }
    }

    private static void Error(){
        throw new DataIntegrityViolationException(ErrorMsg);
    }
}
