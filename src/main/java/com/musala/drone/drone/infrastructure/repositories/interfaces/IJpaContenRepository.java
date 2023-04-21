package com.musala.drone.drone.infrastructure.repositories.interfaces;

import com.musala.drone.drone.infrastructure.entities.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaContenRepository extends JpaRepository<ContentEntity, Long> {
}
