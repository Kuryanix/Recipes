package com.example.web3.service;

import com.example.web3.model.Ingredient;
import org.springframework.web.multipart.MultipartFile;

public interface IngredientService {

    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);

    Ingredient update(long id, Ingredient ingredient);

    Ingredient remove(long id);

    void importIngredients(MultipartFile ingredients);
}
