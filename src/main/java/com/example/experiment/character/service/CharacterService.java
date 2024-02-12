package com.example.experiment.character.service;

import com.example.experiment.character.entity.CharacterEntity;
import com.example.experiment.character.response.CharacterResponse;
import com.example.experiment.character.repository.CharacterJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterService {

    private final CharacterJpaRepository characterJpaRepository;

    public CharacterResponse getCharacterByName(String name) {
        CharacterEntity entity = characterJpaRepository.findByName(name);

        return CharacterResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .species(entity.getSpecies())
                .build();
    }

}
