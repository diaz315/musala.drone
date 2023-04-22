package com.musala.drone.drone.infrastructure.repositories.interfaces;

import com.musala.drone.drone.infrastructure.entities.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJpaContenRepository extends JpaRepository<ContentEntity, Long> {
    @Query("SELECT m FROM ContentEntity m  WHERE m.drone.id = :droneId")
    List<ContentEntity> getContentLoadedByDroneId(@Param("droneId") Long droneId);
}

