package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class MaxDroneCapacity {
    private static String ErrorMsg = "The drone only supports %s grams and the total current content is %s weight";
    public static void Validate(Double droneLimit, Double totalWeightOfContent){
        if(droneLimit<totalWeightOfContent)
            throw new ApplicationException(String.format(ErrorMsg,droneLimit,totalWeightOfContent));
    }
}
