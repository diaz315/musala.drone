package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.model.GenericContent;

import java.util.List;

public interface IContentDroneRepositoryPort {
    public List<DroneContent> GetContentLoadedByDroneId(Long droneid);
    public boolean LoadContentInDrone(Long droneid, List<GenericContent> contentList);
}