package com.example.web3.service;

import com.example.web3.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServices implements RecipeService{

    private final Map<Long, Recipe> recipeMap = new HashMap<>();

    private Long counter = 0L;

    @Override
    public Recipe add(Recipe recipe) {
        recipeMap.put(this.counter++, recipe);
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        return null;
    }
}
