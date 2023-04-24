package com.musala.drone.drone.domain.factories;

import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Medicine;
import com.musala.drone.drone.domain.model.OtherContent;

public class ContentFactory {

    public static Content GetContent(String type)
    {
        Content result = null;
        switch (type.toLowerCase()) {
            case "medicine":
                result = new Medicine();
                break;
            default:
                result = new OtherContent();
        }

        return result;
    }

}

