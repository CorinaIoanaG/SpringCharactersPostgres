package com.accesa.demo.util;

import com.accesa.demo.model.ActuatorResponse;
import com.accesa.demo.model.CharacterModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@EnableScheduling
@Configuration
public class SchedulerActuator {
    @Scheduled(cron = "0 */10 * ? * *")
    private ActuatorResponse callActuator() {
        try {
            URL url = new URL("http://localhost:8080/actuator/health");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (
                        InputStream inputStream = conn.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                    StringBuilder responseBuilder = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        responseBuilder.append(line);
                    }

                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(responseBuilder.toString(), ActuatorResponse.class);
                }
            } else {
                throw new RuntimeException("Failed to fetch response: HTTP " + conn.getResponseCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch response", e);
        }
    }


}
