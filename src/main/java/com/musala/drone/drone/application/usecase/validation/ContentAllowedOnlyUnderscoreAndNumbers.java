package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;

public class ContentAllowedOnlyUnderscoreAndNumbers {
    @Value("${drone.validation.msg.contentallowedonlyunderscoreandnumbers}")
    private static String ErrorMsg;
    public static void Validate(String content){
        if (!content.matches("^[0-9_]+$"))
            throw new ApplicationException(ErrorMsg);
    }
}
