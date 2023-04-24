package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.infrastructure.entities.ContentEntity;
import com.musala.drone.drone.infrastructure.entities.DroneEntity;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaContenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaContenRepositoryImpl implements IContenRepositoryPort {
    private final IJpaContenRepository jpaContenRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public JpaContenRepositoryImpl(IJpaContenRepository jpaContenRepository, ModelMapper modelMapper) {
        this.jpaContenRepository = jpaContenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    //LoadDroneContent
    public List<Content> SaveContent(List<Content> content, Drone drone) {
        var tempContent = content.stream()
                .map(conentenEntity -> modelMapper.map(conentenEntity, ContentEntity.class)).collect(Collectors.toList());

        tempContent.forEach(contententity -> contententity.setDrone(modelMapper.map(drone, DroneEntity.class)));

        var response = jpaContenRepository.saveAll(tempContent);

        var cresp = response.stream()
                .map(conentenEntity -> modelMapper.map(conentenEntity, Content.class)).collect(Collectors.toList());

        return cresp;
    }

    @Override
    public Content SaveContent(Content content, Drone drone) {
        var tempContent = modelMapper.map(content,ContentEntity.class);
        tempContent.setDrone(modelMapper.map(drone,DroneEntity.class));
        var response = jpaContenRepository.save(tempContent);
        var castResult = modelMapper.map(response, Content.class);

        return castResult;
    }

    @Override
    public List<Content> GetGenericContentLoadedByDroneId(Long droneId)
    {
        var result = jpaContenRepository.getContentLoadedByDroneId(droneId);
        var cv = result.stream().map(rentity -> modelMapper.map(rentity, Content.class)).collect(Collectors.toList());
        return cv;
    }
}