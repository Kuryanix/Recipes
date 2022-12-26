package com.example.web3.service;

import com.example.web3.model.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe add(Recipe recipe);

    Recipe get(long id);

    Recipe update(long id, Recipe recipe);

    Recipe remove(long id);

    List<Recipe> getAll();
}
