package com.accesa.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "actuator")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ActuatorResponse {
    @Id
    @GeneratedValue
    private Long id;
    private String status;
    private LocalDateTime dateTime = LocalDateTime.now();
}



