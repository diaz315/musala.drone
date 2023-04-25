package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class ContentAllowedOnlyUnderscoreAndNumbers {
    private static String ErrorMsg = "The code allowed only underscore and numbers";
    public static void Validate(String content){
        if (content == null || content.isEmpty() || !content.matches("^[0-9_]+$"))
            throw new ApplicationException(ErrorMsg);
    }
}
