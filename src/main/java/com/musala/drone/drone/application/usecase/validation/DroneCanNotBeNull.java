package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.domain.model.Drone;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class DroneCanNotBeNull {
    private static String ErrorMsg = "The object drone cant´t be null";

    public static void Validate(Drone drone){
        if(drone == null)
        {
            throw new ApplicationException(ErrorMsg);
        }
    }

}
