package com.musala.drone.drone;

import com.musala.drone.drone.application.usecase.LoadDroneWithMedicationItemsUseCaseImpl;
import com.musala.drone.drone.domain.enums.State;
import com.musala.drone.drone.domain.model.Content;
import com.musala.drone.drone.domain.model.Drone;
import com.musala.drone.drone.domain.model.OtherContent;
import com.musala.drone.drone.domain.ports.out.IContenRepositoryPort;
import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class LoadDroneWithMedicationItemsUseCaseImplTest {
    @Mock
    private IDroneRepositoryPort droneRepository;
    @Mock
    private IContenRepositoryPort contentRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private LoadDroneWithMedicationItemsUseCaseImpl service;

    @Test
    public void LoadDroneShouldBeReturnDroneNotFound()
    {
        Mockito.when(droneRepository.FindDroneById(0L)).thenReturn(null);
        try{
            service.LoadDrone(0L,new ArrayList<>());
        }catch (Exception ex){
            Assertions.assertEquals("Drone Not Found".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnDroneContentEmpty()
    {
        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(new Drone());
        try{
            service.LoadDrone(1L,new ArrayList<>());
        }catch (Exception ex){
            Assertions.assertEquals("you must enter at least one content.".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }


    @Test
    public void LoadDroneShouldBeReturnEmptyContentType()
    {
        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(new Drone());
        try{
            List<Content> data = new ArrayList<>();
            var _content = new OtherContent();
            data.add(_content);
            data.add(_content);

            service.LoadDrone(1L,data);
        }catch (Exception ex){
            Assertions.assertEquals("you must enter the type".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnContentAllowedOnlyLettersNumbers()
    {
        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(new Drone());
        try{
            List<Content> data = new ArrayList<>();
            var _content = new OtherContent();
            _content.setType("medicine");

            data.add(_content);
            data.add(_content);

            service.LoadDrone(1L,data);
        }catch (Exception ex){
            Assertions.assertEquals("the name allowed only letters, numbers, ‘-‘, ‘_’".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnContentAllowedOnlyUnderscoreAndNumbers()
    {
        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(new Drone());
        try{
            List<Content> data = new ArrayList<>();
            var _content = new OtherContent();
            _content.setType("medicine");
            _content.setName("ABC");

            data.add(_content);
            data.add(_content);

            service.LoadDrone(1L,data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            Assertions.assertEquals("the code allowed only underscore and numbers".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }

    @Test
    public void LoadDroneShouldBeReturnBatteryLevelNotEnought()
    {
        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(new Drone());
        try{
            service.MinBatteryDroneToWork = 25;
            List<Content> data = new ArrayList<>();
            var _content = new OtherContent();
            _content.setType("medicine");
            _content.setName("ABC");
            _content.setCode("1234");

            data.add(_content);
            data.add(_content);

            service.LoadDrone(1L,data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            Assertions.assertEquals("the battery level is below 25 percent".toLowerCase(),ex.getMessage().toLowerCase());
        }
    }


    @Test
    public void LoadDroneShouldBeReturnDroneIsBusyLOADED(){
        LoadDroneShouldBeReturnDroneIsBusy(State.LOADED);
    }

    @Test
    public void LoadDroneShouldBeReturnDroneIsBusyRETURNING(){
        LoadDroneShouldBeReturnDroneIsBusy(State.RETURNING);
    }

    @Test
    public void LoadDroneShouldBeReturnDroneIsBusyDELIVERING(){
        LoadDroneShouldBeReturnDroneIsBusy(State.DELIVERING);
    }

    @Test
    public void LoadDroneShouldBeReturnDroneIsBusyDELIVERED(){
        LoadDroneShouldBeReturnDroneIsBusy(State.DELIVERED);
    }


    private void LoadDroneShouldBeReturnDroneIsBusy(State _state)
    {
        var drone = new Drone();
        drone.setBatteryCapacity(100);
        drone.setState(_state);
        drone.setSerialNumber("001_"+_state.name());

        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(drone);
        try{
            service.MinBatteryDroneToWork = 25;
            List<Content> data = new ArrayList<>();
            var _content = new OtherContent();
            _content.setType("medicine");
            _content.setName("ABC");
            _content.setCode("1234");

            data.add(_content);
            data.add(_content);

            service.LoadDrone(1L,data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            Assertions.assertEquals(String.format("the drone with serial %s currently is busy",drone.getSerialNumber()).toLowerCase(),ex.getMessage().toLowerCase());
        }
    }


    @Test
    public void LoadDroneShouldBeReturnMaxDroneCapacity()
    {
        var drone = new Drone();
        drone.setBatteryCapacity(100);
        drone.setState(State.LOADING);
        drone.setSerialNumber("001_"+State.LOADING.name());
        drone.setWeightLimit(100);

        List<Content> data = new ArrayList<>();
        var _content = new OtherContent();
        _content.setType("medicine");
        _content.setName("ABC");
        _content.setCode("1234");
        _content.setWeight(50);

        data.add(_content);
        data.add(_content);

        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(drone);
        Mockito.when(contentRepository.GetGenericContentLoadedByDroneId(1L)).thenReturn(data);
        try{
            service.MinBatteryDroneToWork = 25;

            service.LoadDrone(1L,data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            Assertions.assertEquals(String.format("the drone only supports %s grams and the total current content is %s weight",drone.getWeightLimit(),service.TotalWeightOfContent).toLowerCase(),ex.getMessage().toLowerCase());
        }
        Mockito.verify(contentRepository,Mockito.times(1)).GetGenericContentLoadedByDroneId(1L);
    }

    @Test
    public void LoadDroneShouldBeReturnMaxDroneCapacityWithOutGetGenericContentLoaded()
    {
        var drone = new Drone();
        drone.setBatteryCapacity(100);
        drone.setState(State.IDLE);
        drone.setSerialNumber("001_"+State.IDLE.name());
        drone.setWeightLimit(100);

        List<Content> data = new ArrayList<>();
        var _content = new OtherContent();
        _content.setType("medicine");
        _content.setName("ABC");
        _content.setCode("1234");
        _content.setWeight(20);

        data.add(_content);
        data.add(_content);

        Mockito.when(droneRepository.FindDroneById(1L)).thenReturn(drone);

        try{
            service.MinBatteryDroneToWork = 25;

            service.LoadDrone(1L,data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            Assertions.assertEquals(String.format("the drone only supports %s grams and the total current content is %s weight",drone.getWeightLimit(),service.TotalWeightOfContent).toLowerCase(),ex.getMessage().toLowerCase());
        }

        Mockito.verify(contentRepository,Mockito.times(0)).GetGenericContentLoadedByDroneId(1L); //---->

    }

}
