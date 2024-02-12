package com.example.experiment.character.service;

import com.example.experiment.character.entity.CharacterEntity;
import com.example.experiment.character.response.CharacterResponse;
import com.example.experiment.character.repository.CharacterJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    CharacterJpaRepository characterJpaRepository;

    @InjectMocks
    CharacterService service;

    @Test
    void getCharacterByName_givenName_shouldReturnCharacterResponse() {

        CharacterEntity entity = CharacterEntity.builder()
                .id(1L)
                .name("Sarra")
                .age(29)
                .species("human")
                .build();

        when(characterJpaRepository.findByName("Sarra")).thenReturn(entity);

        CharacterResponse response = service.getCharacterByName("Sarra");

        CharacterResponse expectedResponse = CharacterResponse.builder()
                .id(1L)
                .name("Sarra")
                .age(29)
                .species("human")
                .build();

        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
    }
}
