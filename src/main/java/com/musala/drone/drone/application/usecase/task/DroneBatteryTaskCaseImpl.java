package com.musala.drone.drone.application.usecase.task;

import com.musala.drone.drone.domain.ports.out.IDroneRepositoryPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class DroneBatteryTaskCaseImpl {

    private static final Logger logger = LogManager.getLogger("com.musala.drone.drone.application.usecase.checkDronesBatteryTask");

    @Value("${drone.min.battery.drone.level.to.work}")
    private Integer MinBatteryDroneToWork;

    private final IDroneRepositoryPort repository;

    public DroneBatteryTaskCaseImpl(IDroneRepositoryPort repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRateString = "${drone.miliseconds.to.check.battery.task}")
    public void dischargeBatteriesTask(){
        var result = repository.GetAllDrones();
        if(!result.isEmpty()){
            result.forEach(data->{
                if(data.getBatteryCapacity()>0){
                    data.setBatteryCapacity((data.getBatteryCapacity()-1));
                    repository.SaveDrone(data);
                }
            });
        }
    }

    @Scheduled(fixedRateString = "${drone.miliseconds.to.check.battery.task}")
    public void checkDronesBatteryTask()
    {
        var result = repository.GetAllDrones();
        if(!result.isEmpty()){
            result.forEach(data->
                    {
                        String status = data.getBatteryCapacity() < MinBatteryDroneToWork ? "low":"ok";
                        String msg = String.format("The drone with serial s% , level battery is s%, has s% % charge",data.getSerialNumber(),status,data.getBatteryCapacity());

                        if(data.getBatteryCapacity() < MinBatteryDroneToWork){
                            logger.warn(msg);
                        }else{
                            logger.info(msg);
                        }
                    }
            );
        }else{
            logger.info("No drone available to check");
        }
    }
}
