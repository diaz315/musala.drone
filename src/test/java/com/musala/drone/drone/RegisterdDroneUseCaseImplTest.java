package com.musala.drone.drone;

import com.musala.drone.drone.application.usecase.RegisterdDroneUseCaseImpl;
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
public class RegisterdDroneUseCaseImplTest {
    @Mock
    private IDroneRepositoryPort repository;
    @InjectMocks
    private RegisterdDroneUseCaseImpl service;

    @Test
    public void LoadDroneShouldBeReturnDroneCanNotBeNull()
    {
        try{
            service.SaveDrone(null);
        }catch (Exception ex){
            Assertions.assertEquals("the object drone cant´t be null".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnEmptySerialNumber()
    {
        try{
            Drone drone = new Drone();

            service.SaveDrone(drone);
        }catch (Exception ex){
            Assertions.assertEquals("you must enter the serial number".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnMaxSerialNumberLength()
    {
        try{
            Drone drone = new Drone();
            drone.setSerialNumber("ABCDEFGHIJ");
            for(int i= 0;i<10;i++){
                drone.setSerialNumber(drone.getSerialNumber().concat("ABCDEFGHIJ"));
            }

            service.SaveDrone(drone);
        }catch (Exception ex){
            Assertions.assertEquals("the max value of characters is 100".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnMaxBattery()
    {
        try{
            Drone drone = new Drone();
            drone.setSerialNumber("ABCDEFGHIJ");
            drone.setBatteryCapacity(110);

            service.SaveDrone(drone);
        }catch (Exception ex){
            Assertions.assertEquals("the max battery must be 100".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnMinBattery()
    {
        try{
            Drone drone = new Drone();
            drone.setSerialNumber("ABCDEFGHIJ");
            drone.setBatteryCapacity(-1);

            service.SaveDrone(drone);
        }catch (Exception ex){
            Assertions.assertEquals("the min battery must be 0".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnSerialNumberUnique()
    {
        Drone tmpDrone = new Drone();
        tmpDrone.setSerialNumber("ABCDEFGHIJ");
        tmpDrone.setId(100L);

        Mockito.when(repository.FindBySerialNumber(Mockito.anyString())).thenReturn(tmpDrone);

        try{
            Drone drone = new Drone();
            drone.setSerialNumber("ABCDEFGHIJ");
            drone.setBatteryCapacity(100);
            drone.setId(0L);

            service.SaveDrone(drone);
        }catch (Exception ex){
            Assertions.assertEquals("serial number must be unique".toLowerCase(),ex.getMessage().toLowerCase());
        }
        Mockito.verify(repository,Mockito.times(1)).FindBySerialNumber(Mockito.anyString());
    }
}
