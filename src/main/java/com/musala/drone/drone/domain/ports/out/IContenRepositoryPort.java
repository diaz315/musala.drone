package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.dto.ContentDto;
import com.musala.drone.drone.domain.dto.DroneDto;
import com.musala.drone.drone.domain.model.Medication;

import java.util.List;

public interface IContenRepositoryPort {
    public List<ContentDto> SaveContent(List<ContentDto> content, DroneDto drone);
    public ContentDto SaveContent(ContentDto content, DroneDto drone);

    public List<ContentDto> GetGenericContentLoadedByDroneId(Long contentid);
}
