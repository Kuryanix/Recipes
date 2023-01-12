package com.example.web3.controller;

import com.example.web3.model.Recipe;
import com.example.web3.service.RecipeService;
import io.github.classgraph.ResourceList;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAll() {
        return this.recipeService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getTitle())) {
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым");
        }
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепт найден", content = {
                    @Content(mediaType = "application/json")
            }
            ),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден", content = {})
    }

    )
    public Recipe getRecipe(@PathVariable("id") long id) {
        return recipeService.get(id);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadRecipes() {
        byte[] bytes = recipeService.getAllInBytes();
        if (bytes == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"")
                .body(bytes);
    }

    @PostMapping(value = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importRecipes(MultipartFile recipes) {
        recipeService.importRecipes(recipes);
    }
}
