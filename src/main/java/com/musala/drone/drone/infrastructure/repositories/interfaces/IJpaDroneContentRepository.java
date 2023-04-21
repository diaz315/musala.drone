package com.musala.drone.drone.infrastructure.repositories.interfaces;

import com.musala.drone.drone.infrastructure.entities.DroneContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaDroneContentRepository extends JpaRepository<DroneContentEntity, Long> {
}
