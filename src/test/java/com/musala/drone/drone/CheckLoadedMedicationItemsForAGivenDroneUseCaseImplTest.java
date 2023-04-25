package com.musala.drone.drone;

import com.musala.drone.drone.application.usecase.CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl;
import com.musala.drone.drone.domain.factories.ContentFactory;
import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CheckLoadedMedicationItemsForAGivenDroneUseCaseImplTest {

    @Mock
    private IContenRepositoryPort contentRepository;
    @Mock
    private IDroneRepositoryPort droneRepository;
    @InjectMocks
    private CheckLoadedMedicationItemsForAGivenDroneUseCaseImpl service;

    @Test
    public void CheckLoadedMedicationsShouldBeReturnError(){
        try{
            Mockito.when(droneRepository.FindDroneById(0L)).thenReturn(null);
            service.CheckLoadedMedications(0L);
        }catch (Exception ex){
            Assertions.assertEquals("Drone Not Found".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void CheckLoadedMedicationsShouldBeReturnEmptyList(){
        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(new Drone());
        Mockito.when(contentRepository.GetGenericContentLoadedByDroneId(1L)).thenReturn(new ArrayList<>());

        var rs = service.CheckLoadedMedications(1L);
        Assertions.assertEquals(true,rs.isEmpty());
    }

    @Test
    public void CheckLoadedMedicationsShouldBeReturnList(){
        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(new Drone());

        List<Content> values = new ArrayList<>();
        values.add(ContentFactory.GetContent(""));
        values.add(ContentFactory.GetContent(""));
        values.add(ContentFactory.GetContent(""));

        Mockito.when(contentRepository.GetGenericContentLoadedByDroneId(1L)).thenReturn(values);

        var rs = service.CheckLoadedMedications(1L);
        Assertions.assertFalse(rs.isEmpty());
    }
}
