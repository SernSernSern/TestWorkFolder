import { Component, Input, OnInit } from '@angular/core';
import { Ingredient } from '../ingredient';
import { OptionCategory } from '../option-category';
import { Recipe } from '../recipe';
import { RecipeCategory } from '../recipe-category';
import {Location} from '@angular/common';
import { RecipeServiceService } from '../recipe-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-recipe',
  templateUrl: './update-recipe.component.html',
  styleUrls: ['./update-recipe.component.css']
})
export class UpdateRecipeComponent implements OnInit {

  @Input() recipe: Recipe;

  ingredient: Ingredient = {
    id: 0,
    name: '',
    quantity: ''
  };

  opts: OptionCategory[] = [
    { id: RecipeCategory.STARTER, name: 'Starter' },
    { id: RecipeCategory.MAIN_COURSE, name: 'Main Course' },
    { id: RecipeCategory.DESSERT, name: 'Dessert' }
  ];

  filterCategory(theCategory: RecipeCategory) {
    this.recipe.recipeCategory = theCategory;
  }

  constructor(
    private apiService: RecipeServiceService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.getRecipe();
  }

  getRecipe(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.apiService.getRecipe(id)
      .subscribe(recipe => this.recipe = recipe);
  }

  addToIngredientsList(): void {
    this.recipe.ingredients.push(JSON.parse(JSON.stringify(this.ingredient)));
  }

  deleteFromIngredientsList(ingredient: Ingredient) {
    const indexOfIngredient = this.recipe.ingredients.indexOf(ingredient);
    this.recipe.ingredients.splice(indexOfIngredient, 1);
  }

  sendRecipe(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.apiService.updateRecipe(this.recipe, id).subscribe(
      res => {
        this.location.back();
      },
      err => {
        alert('An error has occurred while updating recipe');
      }
    );
  }
}
