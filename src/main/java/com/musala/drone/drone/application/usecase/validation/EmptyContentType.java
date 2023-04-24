package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;

public class EmptyContentType {
    private static String ErrorMsg = "You must enter the type";
    public static void Validate(String type){
        if(type==null || type.isEmpty())
            throw new ApplicationException(ErrorMsg);
    }
}