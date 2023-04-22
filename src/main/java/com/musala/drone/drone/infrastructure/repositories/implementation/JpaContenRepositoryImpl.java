package com.musala.drone.drone.infrastructure.repositories.implementation;

import com.musala.drone.drone.domain.dto.ContentDto;
import com.musala.drone.drone.domain.model.Medication;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.infrastructure.entities.ContentEntity;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaContenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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
    public List<Medication> SaveContent(List<Medication> content) {
        var tempContent = content.stream()
                .map(conentenEntity -> modelMapper.map(conentenEntity, ContentEntity.class)).collect(Collectors.toList());

        var response = jpaContenRepository.saveAll(tempContent);

        var cresp = response.stream()
                .map(conentenEntity -> modelMapper.map(conentenEntity, Medication.class)).collect(Collectors.toList());

        return cresp;
    }

    @Override
    public ContentDto SaveContent(ContentDto content) {
        var tempContent = modelMapper.map(content,ContentEntity.class);
        var response = jpaContenRepository.save(tempContent);
        var castResult = modelMapper.map(response, ContentDto.class);

        return castResult;
    }

    @Override
    public List<ContentDto> GetGenericContentLoadedByDroneId(Long droneId)
    {
        var result = jpaContenRepository.getContentLoadedByDroneId(droneId);
        var cv = result.stream().map(rentity -> modelMapper.map(rentity,ContentDto.class)).collect(Collectors.toList());
        return cv;
    }
}