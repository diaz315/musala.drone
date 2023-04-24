package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class MinBattery {
    private static String ErrorMsg = "The min battery must be 0";

    public static void Validate(Integer battery){
        if(battery<0)
            throw new ApplicationException(ErrorMsg);
    }
}
