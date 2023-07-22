package com.accesa.demo.service;

import com.accesa.demo.model.ActuatorResponse;
import com.accesa.demo.repository.ActuatorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ActuatorService {
    @Autowired
    ActuatorRepository actuatorRepository;

    public void callActuator() {
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
                    actuatorRepository.save(objectMapper.readValue(responseBuilder.toString(), ActuatorResponse.class));
                }
            } else {
                throw new RuntimeException("Failed to fetch response: HTTP " + conn.getResponseCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch response", e);
        }
    }

}
