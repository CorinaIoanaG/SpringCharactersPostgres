package com.accesa.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class CharacterModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String title;
}
