package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;

public class MaxBattery {
    @Value("${drone.validation.msg.maxbattery}")
    private static String ErrorMsg;
    public static void Validate(Integer battery){
        if(battery>100)
            throw new ApplicationException(ErrorMsg);
    }
}
