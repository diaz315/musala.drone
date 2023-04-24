package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;

public class ContentAllowedOnlyLettersNumbers {
    private static String ErrorMsg = "Allowed only letters, numbers, ‘-‘, ‘_’";
    public static void Validate(String content){
        if (!content.matches("^[a-zA-Z0-9_-]*$"))
            throw new ApplicationException(ErrorMsg);
    }
}
