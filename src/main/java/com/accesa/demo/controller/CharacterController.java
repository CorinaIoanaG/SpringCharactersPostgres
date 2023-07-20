package com.accesa.demo.controller;

import com.accesa.demo.model.CharacterModel;
import com.accesa.demo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public List<CharacterModel> getAllCharacters(){
        return characterService.getAllCharacters();
    }

    @GetMapping("{id}")
    public CharacterModel getById(@PathVariable Long id) {
        return characterService.getById(id);
    }

    @DeleteMapping("{id}")
    public CharacterModel deleteById(@PathVariable Long id) {
        return characterService.deleteById(id);
    }

    @PostMapping
    public CharacterModel add(@RequestBody CharacterModel characterModel) {
        return characterService.add(characterModel);
    }

    @PatchMapping("{id}/update")
    CharacterModel updateCharacter(@PathVariable Long id, @RequestParam String name, @RequestParam String title) {
        return characterService.updateCharacter(id, name, title);
    }


}
