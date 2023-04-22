package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.model.Medication;

import java.util.List;

public interface IContentDroneRepositoryPort {
    public boolean LoadContentInDrone(Long droneid, List<Medication> contentList);
}