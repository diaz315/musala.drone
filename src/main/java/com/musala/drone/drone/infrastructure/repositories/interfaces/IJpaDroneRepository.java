package com.musala.drone.drone.infrastructure.repositories.interfaces;

import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.infrastructure.entities.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJpaDroneRepository extends JpaRepository<DroneEntity, Long> {
    List<DroneEntity> findAllByState(State state);
    DroneEntity findBySerialNumber(String serial);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE DroneEntity c SET c.state = :state WHERE c.id = :id")
    int changeStateDroneById(@Param("id") Long id, @Param("state") State state);
}
