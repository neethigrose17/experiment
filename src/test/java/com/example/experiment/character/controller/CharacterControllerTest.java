package com.example.experiment.character.controller;

import com.example.experiment.character.service.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest
public class CharacterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CharacterService characterService;

    @InjectMocks
    @Autowired
    CharacterController controller;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getCharacterByName_givenName_shouldReturnCharacter() {

    }

}
