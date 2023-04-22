package com.musala.drone.drone.infrastructure.entities;


import com.musala.drone.drone.domain.enums.Model;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.GenericContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentEntity> medications = new ArrayList<>();
}

