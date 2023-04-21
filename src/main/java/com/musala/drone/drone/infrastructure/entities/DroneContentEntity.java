package com.musala.drone.drone.infrastructure.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DroneContentEntity")
public class DroneContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "droneid")
    private Long droneid;

    @Column(name = "contentid")
    private Long contentid;

    @Column(name = "startdate")
    private LocalDateTime startdate;

    @Column(name = "enddate")
    private LocalDateTime enddate;

    @Column(name = "isactive")
    private boolean isactive;
}