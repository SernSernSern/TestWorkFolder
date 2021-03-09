package com.simple.test.work.TestWork.controller;

import com.simple.test.work.TestWork.dto.RecipeDTO;
import com.simple.test.work.TestWork.mapper.RecipeMapper;
import com.simple.test.work.TestWork.model.Recipe;
import com.simple.test.work.TestWork.repo.RecipeRepo;
import com.simple.test.work.TestWork.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



@CrossOrigin
@RestController
public class RecipeController {
    private RecipeService recipeService;
    private RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    @GetMapping(value = "/all/byAccesses")
    public List<RecipeDTO> getAllByNoOfAccesses(){
        List<Recipe> recipes = recipeService.findByOrder();
        return recipes.stream().map(recipeMapper::toRecipeDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/all/byCategory/{theCategory}")
    public List<RecipeDTO> getAllByCategory(@PathVariable String theCategory){
        List<Recipe> recipes = recipeService.findByRecipe(recipeService.findCategory(theCategory));
        return recipes.stream().map(recipeMapper::toRecipeDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/all/byName/{term}")
    public List<RecipeDTO> getAllByName(@PathVariable String term){
        List<Recipe> recipes = recipeService.findByName(term);
        return recipes.stream().map(recipeMapper::toRecipeDto).collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<RecipeDTO> getAll(){
        List<Recipe> recipes = recipeService.findAll();
        return recipes.stream().map(recipeMapper::toRecipeDto).collect(Collectors.toList());
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<RecipeDTO> findById(@PathVariable Long id) {
        if(recipeService.findById(id).isPresent()) {
            RecipeDTO dto = recipeService.findById(id)
                    .map(recipeMapper::toRecipeDto)
                    .get();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/create")
    public ResponseEntity createNewRecipe(@RequestBody RecipeDTO recipeDTO){
        Recipe recipe = recipeMapper.toRecipe(recipeDTO);
        recipeService.insert(recipe);
        return new ResponseEntity(HttpStatus.OK);
    }



    @PutMapping("/create/all")
    public ResponseEntity putAllRecipes(@RequestBody RecipeDTO[] recipesDTO){
        try {
            List<Recipe> recipesList = Arrays.asList(recipesDTO)
                    .stream()
                    .map(recipeMapper::toRecipe)
                    .collect(Collectors.toList());
            Recipe[] recipes = new Recipe[recipesList.size()];
            recipes = recipesList.toArray(recipes);
            recipeService.saveAll(recipes);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity putRecipes(@RequestBody RecipeDTO recipeDTO, @PathVariable long id){
        try {
            Recipe recipe = recipeMapper.toRecipe(recipeDTO);
            recipeService.save(recipe, id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRecipe(@PathVariable Long id) {
        if(recipeService.findById(id).isPresent()) {
            recipeService.findById(id).get();
            recipeService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
