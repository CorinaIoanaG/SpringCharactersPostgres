package com.accesa.demo.repository;

import com.accesa.demo.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel,Long> {
    List<CharacterModel> findByName(String name);
}
