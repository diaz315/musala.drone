package com.musala.drone.drone;

import com.musala.drone.drone.application.usecase.ChangeStateDroneUseCaseImpl;
import com.musala.drone.drone.domain.enums.State;
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

@ExtendWith(MockitoExtension.class)
public class ChangeStateDroneUseCaseImplTest {

    @Mock
    private IDroneRepositoryPort repository;
    @Mock
    private IContenRepositoryPort contentRepository;


    @InjectMocks
    private ChangeStateDroneUseCaseImpl service;

    @Test
    public void ChangeStateDroneShouldBeReturnTrue(){
        Drone drone = new Drone();
        Mockito.when(repository.FindDroneById(Mockito.anyLong())).thenReturn(drone);
        Mockito.when(repository.ChangeStateDrone(1L, State.DELIVERED)).thenReturn(true);

        boolean result = service.ChangeStateDrone(1L,State.DELIVERED);

        Mockito.verify(contentRepository, Mockito.times(1)).UpdatedContentDeliveredStatus(Mockito.anyLong());
        Assertions.assertEquals(result,true);
    }

    @Test
    public void ChangeStateDroneShouldBeReturnDroneNotFound(){
        Mockito.when(repository.FindDroneById(Mockito.anyLong())).thenReturn(null).thenThrow();

        try{
            service.ChangeStateDrone(1L,State.DELIVERED);
        }catch (Exception ex){
            Assertions.assertEquals("Drone Not Found".toLowerCase(),ex.getMessage().toLowerCase());
        }

    }


}
