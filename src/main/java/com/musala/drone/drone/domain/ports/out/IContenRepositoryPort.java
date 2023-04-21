package com.musala.drone.drone.domain.ports.out;

import com.musala.drone.drone.domain.model.GenericContent;

import java.util.List;

public interface IContenRepositoryPort {
    public List<GenericContent> SaveContent(List<GenericContent> content);
    public GenericContent SaveContent(GenericContent content);
}
