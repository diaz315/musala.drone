package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.model.Medication;

import java.util.List;

public interface IContenRepositoryPort {
    public List<Medication> SaveContent(List<Medication> content);
    public Medication SaveContent(Medication content);

    public List<Medication> GetGenericContentLoadedByDroneId(Long contentid);

}
