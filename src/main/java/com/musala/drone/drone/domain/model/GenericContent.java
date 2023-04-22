package com.musala.drone.drone.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class GenericContent {
    private Long id;
    private String type;
    private String name;
    private double weight;
    private String code;
    private String image;
    private Drone drone;

    public GenericContent()
    {
        type = GetClassification();
    }
    protected String GetClassification(){
        return this.getClass().getSimpleName();
    }
}
