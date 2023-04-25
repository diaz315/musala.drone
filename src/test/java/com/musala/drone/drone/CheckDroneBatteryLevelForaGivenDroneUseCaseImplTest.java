package com.musala.drone.drone;

import com.musala.drone.drone.application.usecase.CheckDroneBatteryLevelForaGivenDroneUseCaseImpl;
import com.musala.drone.drone.application.usecase.validation.DroneNotFound;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CheckDroneBatteryLevelForaGivenDroneUseCaseImplTest
{
    @Mock
    private IDroneRepositoryPort repository;

    @InjectMocks
    private CheckDroneBatteryLevelForaGivenDroneUseCaseImpl service;

    @Test
    public void CheckDroneBatteryShouldReturnError(){
        Mockito.when(repository.FindDroneById(0L)).thenReturn(null);
        try{
            service.CheckDroneBattery(0L);
        }catch (Exception ex){
            Assertions.assertEquals("Drone Not Found".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void CheckDroneBatteryShouldReturnInteger(){
        Mockito.when(repository.FindDroneById(1L)).thenReturn(new Drone());
        var battery = service.CheckDroneBattery(1L);
        Assertions.assertEquals(0,battery.get());
    }

    @Test
    public void SetBatteryChargeShouldReturnError(){
        Mockito.when(repository.FindDroneById(0L)).thenReturn(null);

        try{
            service.SetBatteryCharge(0L,0);
        }catch (Exception ex){
            Assertions.assertEquals("Drone Not Found".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void CheckDroneBatteryShouldReturnErrorMinBattery(){
        Mockito.when(repository.FindDroneById(1L)).thenReturn(new Drone());

        try{
            service.SetBatteryCharge(1L,-10);
        }catch (Exception ex){
            Assertions.assertEquals("The min battery must be 0".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void CheckDroneBatteryShouldReturnErrorMaxBattery(){
        Mockito.when(repository.FindDroneById(1L)).thenReturn(new Drone());

        try{
            service.SetBatteryCharge(1L,1010);
        }catch (Exception ex){
            Assertions.assertEquals("The max battery must be 100".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

}
