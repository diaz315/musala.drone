package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.domain.model.Drone;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DroneNotFound {
    private static String ErrorMsg = "Drone not found";
    public static void Validate(Drone drone){
        if (drone == null)
        {
            throw new EntityNotFoundException(ErrorMsg);
        }
    }
}
