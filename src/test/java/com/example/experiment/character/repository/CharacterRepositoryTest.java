package com.example.experiment.character.repository;

import com.example.experiment.character.entity.CharacterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CharacterRepositoryTest {

    @Autowired
    CharacterJpaRepository repository;

    @Test
    void findByName_givenName_shouldReturnCharacter() {

        CharacterEntity actual = repository.findByName("Sarra");

        CharacterEntity expected = CharacterEntity.builder()
                .id(1L)
                .name("Sarra")
                .age(29)
                .species("human")
                .build();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void findByName_givenInvalidName_shouldReturnZeroResults() {
        CharacterEntity actual = repository.findByName("Invalid Name");

        assertThat(actual).isNull();
    }

    @Test
    void findById_givenId_shouldReturnCharacter() {
        CharacterEntity actual = repository.findById(1L).orElseThrow();

        CharacterEntity expected = CharacterEntity.builder()
                .id(1L)
                .name("Sarra")
                .age(29)
                .species("human")
                .build();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void findById_givenInvalidId_shouldReturnZeroResults() {
        Optional<CharacterEntity> actual = repository.findById(0L);

        assertThat(actual).isEmpty();
    }
}
