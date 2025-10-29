package com.example.experiment.character.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "character")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asdlkfjasl;
    private Integer age;
    private String sex;
    private String species;


    private int position;

    public void move(int change) {
        setPosition(position + change);
    }
}
