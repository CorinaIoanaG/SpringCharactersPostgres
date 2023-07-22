package com.accesa.demo.util;

import com.accesa.demo.service.ActuatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@EnableScheduling
@Configuration
public class SchedulerActuator {
    @Autowired
    ActuatorService actuatorService;

    @Scheduled(cron = "0 */10 * ? * *")
    public void callActuatorEvery10Min() {
        actuatorService.callActuator();
    }

}
