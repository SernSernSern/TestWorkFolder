package com.simple.test.work.TestWork.dto;
import com.simple.test.work.TestWork.model.RecipeCategory;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class RecipeDTO extends RepresentationModel<RecipeDTO> {

    private Long id;

    private String name;

    private String description;


    private List<IngredientDTO> ingredients = new ArrayList<>();

    private String instructions;

    private Date lastAccessed;

    private long noOfTimesAccessed;

}
