package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class MaxBattery {
    private static String ErrorMsg = "The max battery must be 100";
    public static void Validate(Integer battery){
        if(battery>100)
            throw new ApplicationException(ErrorMsg);
    }
}
