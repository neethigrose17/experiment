package com.example.experiment.persistence.character;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterJpaRepository extends JpaRepository<CharacterEntity, Long> {
    CharacterEntity findByName(String name);

}