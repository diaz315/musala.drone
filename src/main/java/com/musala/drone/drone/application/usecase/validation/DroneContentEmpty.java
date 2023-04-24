package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.domain.model.Content;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DroneContentEmpty {
    private static String ErrorMsg = "You must enter at least one content.";
    public static void Validate(List<Content> contentList){
        if(contentList.isEmpty())
            throw new ApplicationException(ErrorMsg);
    }
}
