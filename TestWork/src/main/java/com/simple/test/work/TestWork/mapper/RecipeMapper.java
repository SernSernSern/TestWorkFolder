package com.simple.test.work.TestWork.mapper;

import com.simple.test.work.TestWork.dto.RecipeDTO;
import com.simple.test.work.TestWork.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDTO toRecipeDto(Recipe recipe);
    List<RecipeDTO> toRecipeDTOs(List<Recipe> recipes);
    Recipe toRecipe(RecipeDTO recipeDTO);
}
