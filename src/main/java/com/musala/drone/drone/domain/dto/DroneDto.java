package com.musala.drone.drone.domain.dto;

import com.musala.drone.drone.domain.enums.Model;
import com.musala.drone.drone.domain.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneDto {
    private Long id;
    private String serialNumber;
    private Model model;
    private double weightLimit;
    private int batteryCapacity;
    private State state;
}
