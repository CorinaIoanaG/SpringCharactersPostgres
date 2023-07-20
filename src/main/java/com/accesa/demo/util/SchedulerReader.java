package com.accesa.demo.util;

import com.accesa.demo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

    @EnableScheduling
    @Configuration
    public class SchedulerReader {
        @Autowired
        CharacterService characterService;

        @Scheduled(cron = "0 */10 * ? * *")
        public void readCharactersEvery10Min(){
            characterService.getCharactersFromUrl();
        }
    }

