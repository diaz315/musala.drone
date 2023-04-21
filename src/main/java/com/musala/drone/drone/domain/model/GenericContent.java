package com.musala.drone.drone.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericContent {
    private Long id;
    private String name;
    private double weight;
    private String code;
    private String image;

    protected String GetClassification(){
        return this.getClass().getSimpleName();
    }
}
