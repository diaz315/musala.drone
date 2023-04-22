package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.dto.ContentDto;
import com.musala.drone.drone.domain.model.Medication;

import java.util.List;

public interface IContenRepositoryPort {
    public List<Medication> SaveContent(List<Medication> content);
    public ContentDto SaveContent(ContentDto content);

    public List<ContentDto> GetGenericContentLoadedByDroneId(Long contentid);
}
