package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MinBattery {
    @Value("${drone.validation.msg.minbattery}")
    private static String ErrorMsg;

    public static void Validate(Integer battery){
        if(battery<0)
            throw new ApplicationException(ErrorMsg);
    }
}
