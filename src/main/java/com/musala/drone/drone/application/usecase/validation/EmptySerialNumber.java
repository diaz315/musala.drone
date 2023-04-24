package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmptySerialNumber {
    @Value("${drone.validation.msg.emptyserialnumber}")
    private static String ErrorMsg;
    public static void Validate(String serialNumber){
        if(serialNumber.isEmpty())
            throw new ApplicationException(ErrorMsg);
    }
}
