package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.factories.ContentFactory;
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

import java.util.ArrayList;
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

        List<ContentEntity> response = jpaContenRepository.saveAll(tempContent);

        List<Content> castList = new ArrayList<>();
        response.forEach(tcontent->{
            var type = ContentFactory.GetContent(tcontent.getType());
            castList.add(modelMapper.map(tcontent, type.getClass()));
        });

        return castList;
    }

    @Override
    public Content SaveContent(Content content, Drone drone) {
        var tempContent = modelMapper.map(content,ContentEntity.class);
        tempContent.setDrone(modelMapper.map(drone,DroneEntity.class));
        var response = jpaContenRepository.save(tempContent);

        var type = ContentFactory.GetContent(content.getType());
        var castResult = modelMapper.map(response, type.getClass());

        return castResult;
    }

    @Override
    public List<Content> GetGenericContentLoadedByDroneId(Long droneId)
    {
        var result = jpaContenRepository.getContentLoadedByDroneId(droneId);

        List<Content> castList = new ArrayList<>();

        if(result!=null && !result.isEmpty()){

            result.forEach(content->{
                var type = ContentFactory.GetContent(content.getType());
                castList.add(modelMapper.map(content, type.getClass()));
            });
        }

        return castList;
    }

    @Override
    public void UpdatedContentDeliveredStatus(Long droneId) {
        jpaContenRepository.updatedContentDeliveredStatus(droneId);
    }
}