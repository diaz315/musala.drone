package com.musala.drone.drone.domain.dto;

import com.musala.drone.drone.domain.model.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneContentDto {

    private Long droneid;
    private List<ContentDto> contentList;
}
