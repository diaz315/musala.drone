package com.musala.drone.drone.infrastructure.seeders;

import com.musala.drone.drone.domain.enums.Model;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.infrastructure.entities.DroneEntity;
import com.musala.drone.drone.infrastructure.repositories.interfaces.IJpaDroneRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DroneSeeder {

    @Autowired
    private static IJpaDroneRepository repo = null;

    public DroneSeeder(IJpaDroneRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public static void run() {
        DroneEntity drone1 = new DroneEntity(0L, "001", Model.CRUISERWEIGHT,(double)100,100, State.IDLE, new ArrayList<>());
        DroneEntity drone2 = new DroneEntity(0L, "002", Model.CRUISERWEIGHT,(double)100,100, State.IDLE, new ArrayList<>());
        DroneEntity drone3 = new DroneEntity(0L, "003", Model.CRUISERWEIGHT,(double)100,100, State.IDLE, new ArrayList<>());
        DroneEntity drone4 = new DroneEntity(0L, "004", Model.CRUISERWEIGHT,(double)100,100, State.IDLE, new ArrayList<>());

        repo.save(drone1);
        repo.save(drone2);
        repo.save(drone3);
        repo.save(drone4);
    }
}
