package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MaxSerialNumberLength {
    @Value("${drone.validation.msg.maxserialnumberlength}")
    private static String ErrorMsg;

    public static void Validate(String serialNumber){
        if(serialNumber.length()>100)
            throw new ApplicationException(ErrorMsg);
    }
}
