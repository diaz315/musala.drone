package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class EmptySerialNumber {
    private static String ErrorMsg = "You must enter the serial number";
    public static void Validate(String serialNumber){
        if(serialNumber.isEmpty())
            throw new ApplicationException(ErrorMsg);
    }
}
