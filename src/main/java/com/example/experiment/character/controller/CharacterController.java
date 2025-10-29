package com.example.experiment.character.controller;

import com.example.experiment.character.response.CharacterResponse;
import com.example.experiment.character.service.CharacterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/character")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    public ResponseEntity<CharacterResponse> getCharacterbyName(@RequestParam String name) {
        log.info("In CharacterController, getting character by name {}", name);
        return ResponseEntity.ok(characterService.getCharacterByName(name));
    }

    @PutMapping("/move")
    public ResponseEntity<CharacterResponse> moveCharacter(@RequestParam String name, @RequestParam int change) {
        log.info("In CharacterController, moving {} by {}", name, change);
        return ResponseEntity.ok(characterService.moveCharacter(name, change));
    }
}
