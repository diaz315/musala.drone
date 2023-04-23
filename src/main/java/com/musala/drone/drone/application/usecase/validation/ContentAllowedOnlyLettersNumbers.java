package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;

public class ContentAllowedOnlyLettersNumbers {
    @Value("${drone.validation.msg.contentallowedonlylettersnumbers}")
    private static String ErrorMsg;
    public static void Validate(String content){
        if (!content.matches("^[a-zA-Z0-9_-]*$"))
            throw new ApplicationException(ErrorMsg);
    }
}
