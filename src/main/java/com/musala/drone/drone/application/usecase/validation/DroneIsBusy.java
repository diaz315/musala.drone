package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.domain.enums.State;
import org.springframework.stereotype.Component;

@Component
public class DroneIsBusy {
    private static String ErrorMsg ="The drone with serial %s currently is busy";
    public static void Validate(State droneState,String serial){
        if(droneState != State.IDLE && droneState != State.LOADING)
            throw new ApplicationException(String.format(ErrorMsg,serial));
    }
}
