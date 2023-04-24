package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class MaxSerialNumberLength {
    private static String ErrorMsg = "The max value of characters is 100";

    public static void Validate(String serialNumber){
        if(serialNumber.length()>100)
            throw new ApplicationException(ErrorMsg);
    }
}
