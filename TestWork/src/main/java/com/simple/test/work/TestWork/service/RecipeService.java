package com.simple.test.work.TestWork.service;

import com.simple.test.work.TestWork.model.Recipe;
import com.simple.test.work.TestWork.model.RecipeCategory;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> findAll();

    Optional<Recipe> findById(Long id);

    Recipe insert(Recipe recipe);

    Recipe save(Recipe recipe, Long id);

    Recipe[] saveAll(Recipe[] recipe);

    void delete(Long id);

    RecipeCategory findCategory(String category);

    List<Recipe> findByName(String recipeName);
    List<Recipe> findByOrder();
    List<Recipe> findByRecipe(RecipeCategory recipeCategory);


}
