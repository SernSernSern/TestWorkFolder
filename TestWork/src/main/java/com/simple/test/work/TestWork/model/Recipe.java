package com.simple.test.work.TestWork.model;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated
    private RecipeCategory recipeCategory;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<Ingredient> ingredient = new ArrayList<>();

    private String instructions;

    private Date lastAccessed;

    private long noOfTimesAccessed;

    public Recipe(String name, RecipeCategory recipeCategory, String instructions) {
        this.name = name;
        this.recipeCategory = recipeCategory;
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredientsList(List<Ingredient> ingredient) {
        this.ingredient.clear();
        if(ingredient != null)
            this.ingredient.addAll(ingredient);
    }
}
