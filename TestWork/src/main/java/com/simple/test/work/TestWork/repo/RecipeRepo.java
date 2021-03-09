package com.simple.test.work.TestWork.repo;

import com.simple.test.work.TestWork.model.Recipe;
import com.simple.test.work.TestWork.model.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByRecipeCategory(RecipeCategory recipeCategory);
    List<Recipe> findAllByNameContainingIgnoreCase(String recipeName);
    List<Recipe> findAllByOrderByNoOfTimesAccessedDesc();
}
