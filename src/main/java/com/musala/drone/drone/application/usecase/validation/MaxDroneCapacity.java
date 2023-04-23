package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;

public class MaxDroneCapacity {
    @Value("${drone.validation.msg.maxdronecapacity}")
    private static String ErrorMsg;
    public static void Validate(Double droneLimit, Double totalWeightOfContent){
        if(droneLimit<totalWeightOfContent)
            throw new ApplicationException(String.format(ErrorMsg,droneLimit,totalWeightOfContent));
    }
}
