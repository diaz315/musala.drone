package com.musala.drone.drone.infrastructure.repositories.interfaces;

import com.musala.drone.drone.infrastructure.entities.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJpaContenRepository extends JpaRepository<ContentEntity, Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ContentEntity c SET c.isDelivered = true WHERE c.drone.id = :droneId and (c.isDelivered IS NULL OR c.isDelivered = false)")
    void updatedContentDeliveredStatus(@Param("droneId") Long droneId);

    @Query("SELECT c FROM ContentEntity c WHERE c.drone.id = :droneId AND (c.isDelivered = false OR c.isDelivered IS NULL)")
    List<ContentEntity> getContentLoadedByDroneId(@Param("droneId") Long droneId);

}

