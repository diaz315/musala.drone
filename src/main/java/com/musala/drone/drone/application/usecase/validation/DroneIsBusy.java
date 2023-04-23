package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.domain.enums.State;
import org.springframework.beans.factory.annotation.Value;

public class DroneIsBusy {
    @Value("${drone.validation.msg.droneisbusy}")
    private static String ErrorMsg;
    public static void Validate(State droneState,String serial){
        if(droneState != State.IDLE && droneState != State.LOADING)
            throw new ApplicationException(String.format(ErrorMsg,serial));
    }
}
