package com.musala.drone.drone.infrastructure.entities;


import com.musala.drone.drone.domain.enums.Model;
import com.musala.drone.drone.domain.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DroneEntity")
public class DroneEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serial_number", unique = true)
    private String serialNumber;
    @Column(name = "model")
    private Model model;
    @Column(name = "weightLimit")
    private double weightLimit;
    @Column(name = "batteryCapacity")
    private int batteryCapacity;
    @Column(name = "state")
    private State state;
}

