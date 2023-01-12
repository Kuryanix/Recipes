package com.example.web3.controller;

import com.example.web3.model.Ingredient;
import com.example.web3.model.Recipe;
import com.example.web3.service.IngredientService;
import com.example.web3.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.add(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") long id) {
        return ingredientService.get(id);
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }

    @PostMapping(value = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importIngredients(MultipartFile ingredients) {
        ingredientService.importIngredients(ingredients);

    }
}