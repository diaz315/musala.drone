package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class BatteryLevelNotEnought {
    private static String ErrorMsg = "The battery level is below %s percent";

    public static void Validate(Integer currentBattery, Integer minBatteryToWork){
        if(currentBattery < minBatteryToWork)
            throw new ApplicationException(String.format(ErrorMsg,minBatteryToWork));
    }
}
