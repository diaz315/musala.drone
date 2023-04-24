package com.musala.drone.drone.domain.model;

import com.musala.drone.drone.domain.model.Drone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Content {
    private Long id;
    private String name;
    private double weight;
    private String code;
    private String image;
    private String type;
    private Boolean isDelivered;
}
