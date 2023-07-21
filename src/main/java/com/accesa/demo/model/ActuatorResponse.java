package com.accesa.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ActuatorResponse {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String status;
    @Column
    private LocalDateTime dateTime;

    public ActuatorResponse(String status) {
        this.status = status;
        this.dateTime = LocalDateTime.now();
    }
}


