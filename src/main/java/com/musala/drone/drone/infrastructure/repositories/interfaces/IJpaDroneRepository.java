package com.musala.drone.drone.infrastructure.repositories.interfaces;

import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.infrastructure.entities.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJpaDroneRepository extends JpaRepository<DroneEntity, Long> {
    List<DroneEntity> findAllByState(State state);
}
