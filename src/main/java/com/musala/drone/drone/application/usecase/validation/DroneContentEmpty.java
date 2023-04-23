package com.musala.drone.drone.application.usecase.validation;

import com.musala.drone.drone.application.exception.ApplicationException;
import com.musala.drone.drone.domain.model.Content;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class DroneContentEmpty {
    @Value("${drone.validation.msg.dronecontentempty}")
    private static String ErrorMsg;
    public static void Validate(List<Content> contentList){
        if(contentList.isEmpty())
            throw new ApplicationException(ErrorMsg);
    }
}
