package com.accesa.demo.service;

import com.accesa.demo.model.CharacterModel;
import com.accesa.demo.repository.CharacterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public void getCharactersFromUrl() {
        String url = "https://thronesapi.com/api/v1/Characters";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            // Citim datele JSON de la URL și le transformăm într-un JsonNode
            rootNode = mapper.readTree(new URL(url));
        } catch (IOException e) {
            throw new RuntimeException("Could not read from URL: " + e.getMessage());
        }

        ObjectReader objectReader = mapper.readerFor(new TypeReference<List<CharacterModel>>() {});

        try {
            // Transformăm JsonNode-ul într-o List<CharacterModel>
            List<CharacterModel> characters = objectReader.readValue(rootNode);

            // Salvăm fiecare personaj în baza de date folosind CharacterRepository
            for (CharacterModel character : characters) {
                characterRepository.save(character);
            }

            System.out.println("Success saving datas.");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Processing JSON error " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Reading JSON error " + e.getMessage());
        }
    }

    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }

    public CharacterModel getById(Long characterId) {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character missing "));
    }

    public CharacterModel deleteById(Long characterId) {
        CharacterModel characterToBeDeleted = getById(characterId);
        characterRepository.deleteById(characterId);
        return characterToBeDeleted;
    }

    public CharacterModel add(CharacterModel characterModel) {
        if (characterModel.getName() == null || characterModel.getTitle() == null) {
            throw new RuntimeException("Character has null fields");
        }
        if (!characterRepository.findByName(characterModel.getName()).isEmpty()) {
            throw new RuntimeException("Character Name already exists");
        }
        return characterRepository.save(characterModel);
    }

    public CharacterModel updateCharacter(Long characterId, String name, String title) {
        if (name.isBlank() || title.isBlank()) {
            throw new RuntimeException("Character has null fields");
        }
        CharacterModel characterToBeUpdated = getById(characterId);
        characterToBeUpdated.setName(name);
        characterToBeUpdated.setTitle(title);
        return characterRepository.save(characterToBeUpdated);
    }

}
