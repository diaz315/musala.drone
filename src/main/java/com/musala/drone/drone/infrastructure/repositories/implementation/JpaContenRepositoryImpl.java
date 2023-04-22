package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.model.GenericContent;
import com.musala.drone.drone.domain.model.Medication;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaContenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaContenRepositoryImpl implements IContenRepositoryPort {
    private final IJpaContenRepository jpaContenRepository;

    @Autowired
    public JpaContenRepositoryImpl(IJpaContenRepository jpaContenRepository) {
        this.jpaContenRepository = jpaContenRepository;
    }

    @Override
    public List<Medication> SaveContent(List<Medication> content) {
        return null;
    }

    @Override
    public Medication SaveContent(Medication content) {
        return null;
    }

    @Override
    public List<Medication> GetGenericContentLoadedByDroneId(Long contentid) {
        return null;
    }
}