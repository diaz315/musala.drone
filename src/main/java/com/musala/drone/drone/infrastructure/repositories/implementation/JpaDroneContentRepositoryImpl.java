package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.model.Medication;
import com.musala.drone.drone.domain.ports.out.IContentDroneRepositoryPort;
import com.musala.drone.drone.infrastructure.entities.DroneContentEntity;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaDroneContentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaDroneContentRepositoryImpl implements IContentDroneRepositoryPort {
    private final IJpaDroneContentRepository jpaDroneContentRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public JpaDroneContentRepositoryImpl(IJpaDroneContentRepository jpaDroneContentRepository, ModelMapper modelMapper) {
        this.jpaDroneContentRepository = jpaDroneContentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean LoadContentInDrone(Long droneid, List<Medication> contentList)
    {
        var data = new DroneContent();
        data.setDroneid(droneid);

        List<DroneContent> dataToSave = new ArrayList<>();

        for (var current: contentList)
        {
            var tmp = new DroneContent();
            tmp.setDroneid(droneid);
            tmp.setContentid(current.getId());
            dataToSave.add(tmp);
        }

        var molk = dataToSave.stream()
                .map(droneContentEntity -> modelMapper.map(droneContentEntity, DroneContentEntity.class))
                .collect(Collectors.toList());

        jpaDroneContentRepository.saveAll(molk);

        return true;
    }
}
