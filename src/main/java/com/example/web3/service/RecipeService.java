package com.example.web3.service;

import com.example.web3.model.Recipe;

public interface RecipeService {

    Recipe add(Recipe recipe);

    Recipe get(long id);
}
