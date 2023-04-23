package com.musala.drone.drone.infrastructure.controllers;


import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.DroneContent;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.ports.in.services.IDroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drone")

public class DroneController {
    private final IDroneService droneService;

    @Autowired
    public DroneController(IDroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/getavailabledrones")
    @Operation(summary = "Get Avalaible Dron")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })

    public ResponseEntity<List<Drone>> GetAvailableDrones() {
        var result = droneService.GetAvailableDrones();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkdronebattery")
    @Operation(summary = "Check battery drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })

    public ResponseEntity<Optional<Integer>> CheckDroneBattery(Long droneid) {
        var result = droneService.CheckDroneBattery(droneid);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkloadedmedication")
    @Operation(summary = "Check Loaded content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })

    public ResponseEntity<List<Content>> CheckLoadedMedication(Long droneid) {
        var result = droneService.CheckLoadedMedications(droneid);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/loaddrone")
    @Operation(summary = "Load Dron")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })

    public ResponseEntity<Boolean> LoadDrone(@RequestBody DroneContent content) throws Exception {
        var result = droneService.LoadDrone(content.getDroneid(),content.getContentList());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addrone")
    @Operation(summary = "Add new drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })

    public ResponseEntity<Drone> Addrone(@RequestBody Drone drone) {
        Drone result = droneService.SaveDrone(drone);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/changestatedrone")
    @Operation(summary = "Change state drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })

    public ResponseEntity<Boolean> CheckDroneBattery(Long droneid, State state) {
        var result = droneService.ChangeStateDrone(droneid,state);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/setchargebattery")
    @Operation(summary = "Charge drone battery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })

    public void CheckDroneBattery(Long droneId, Integer battery) {
        droneService.SetBatteryCharge(droneId,battery);
    }

}
