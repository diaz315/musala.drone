package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Drone;

import java.util.List;

public interface IContenRepositoryPort {
    public List<Content> SaveContent(List<Content> content, Drone drone);
    public Content SaveContent(Content content, Drone drone);

    public List<Content> GetGenericContentLoadedByDroneId(Long contentid);
    public void UpdatedContentDeliveredStatus(Long contentid);
}
