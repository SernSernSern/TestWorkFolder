package com.simple.test.work.TestWork.service;

import com.simple.test.work.TestWork.model.Recipe;
import com.simple.test.work.TestWork.model.RecipeCategory;
import com.simple.test.work.TestWork.repo.RecipeRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{
   private final RecipeRepo recipeRepo;

    public RecipeServiceImpl(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public List<Recipe> findAll() {
        return this.recipeRepo.findAll();
    }

    @Override
    public Optional<Recipe> findById(Long id) {

        return recipeRepo.findById(id);
    }

    @Override
    public RecipeCategory findCategory(String category) {
        RecipeCategory recipeCategory = RecipeCategory.STARTER;
        if(category.equals("1"))
            recipeCategory = RecipeCategory.MAIN_COURSE;
        if(category.equals("2"))
            recipeCategory = RecipeCategory.DESSERT;

        return recipeCategory;
    }

    @Override
    public Recipe insert(Recipe recipe) {
        recipe.setLastAccessed(new Date());
        return recipeRepo.save(recipe);
    }


    @Override
    public List<Recipe> findByName(String recipeName) {
        return recipeRepo.findAllByNameContainingIgnoreCase(recipeName);
    }

    @Override
    public List<Recipe> findByOrder() {
        return recipeRepo.findAllByOrderByNoOfTimesAccessedDesc();
    }

    @Override
    public List<Recipe> findByRecipe(RecipeCategory recipeCategory) {
        return recipeRepo.findAllByRecipeCategory(recipeCategory);
    }

    @Override
    public Recipe[] saveAll(Recipe[] recipes) {
        List<Recipe> saved = recipeRepo.saveAll(Arrays.asList(recipes));
        return saved.toArray(new Recipe[saved.size()]);
    }



    @Override
    public Recipe save(Recipe recipe, Long id) {
        Optional<Recipe> recipeOptional =recipeRepo.findById(id);
        if(recipeOptional.isPresent()){
            Recipe saver = recipeOptional.get();
            copyRecipe(saver, recipe);
            recipeRepo.save(saver);
            return saver;
        }else {
            return null;
        }
    }


    private void copyRecipe(Recipe recipe1, Recipe recipe2) {
        recipe1.setName(recipe2.getName());
        recipe1.setInstructions(recipe2.getInstructions());
        recipe1.setRecipeCategory(recipe2.getRecipeCategory());
        recipe1.setIngredient(recipe2.getIngredient());
        recipe1.setLastAccessed(recipe2.getLastAccessed());
        recipe1.setNoOfTimesAccessed(recipe2.getNoOfTimesAccessed());
    }

    @Override
    public void delete(Long id) {
        recipeRepo.deleteById(id);
    }
}
