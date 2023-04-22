package com.musala.drone.drone.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneContent {

    private Long droneid;
    private List<Content> contentList;
}
