package com.example.experiment.character.controller;

import com.example.experiment.character.response.CharacterResponse;
import com.example.experiment.character.service.CharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void getCharacterByName_givenName_shouldReturnCharacter() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        CharacterResponse expected = CharacterResponse.builder()
                .id(1L)
                .name("Sarra")
                .age(29)
                .sex("female")
                .species("human")
                .position(0)
                .build();

        when(characterService.getCharacterByName("Sarra")).thenReturn(expected);

        mockMvc.perform(get("/api/character")
                .param("name", "Sarra")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }
}
