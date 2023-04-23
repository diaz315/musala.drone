package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.domain.model.Drone;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;

public class DroneNotFound {
    @Value("${drone.validation.msg.dronenotfound}")
    private static String ErrorMsg;
    public static void Validate(Drone drone){
        if (drone == null)
        {
            throw new EntityNotFoundException(ErrorMsg);
        }
    }
}
