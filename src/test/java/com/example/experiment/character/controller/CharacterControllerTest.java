package com.example.experiment.character.controller;

import com.example.experiment.character.response.CharacterResponse;
import com.example.experiment.character.service.CharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CharacterControllerTest {

    @Mock
    CharacterService characterService;

    @InjectMocks
    CharacterController controller;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getCharacterByName_givenName_shouldReturnCharacter() throws Exception {

        CharacterResponse expected = CharacterResponse.builder()
                .id(1L)
                .name("Sarra")
                .age(29)
                .sex("female")
                .species("human")
                .position(0)
                .build();

        when(characterService.getCharacterByName("Sarra")).thenReturn(expected);

        CharacterResponse response = controller.getCharacterbyName("Sarra").getBody();

        System.out.println(response);

        assertThat(response).usingRecursiveComparison().isEqualTo(expected);

        mockMvc.perform(get("/api/character")
                .param("name", "Sarra")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)))
                .andDo(print());
    }

    @Test
    void moveCharacter_shouldReturnMovedCharacter() throws Exception {

        CharacterResponse movedCharacter = CharacterResponse.builder()
                .id(1L)
                .name("Sarra")
                .age(29)
                .sex("female")
                .species("human")
                .position(3)
                .build();

        when(characterService.moveCharacter("Sarra", 3)).thenReturn(movedCharacter);

        mockMvc.perform(put("/api/character/move")
                        .param("name", "Sarra")
                        .param("change", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(movedCharacter)));
    }
}
