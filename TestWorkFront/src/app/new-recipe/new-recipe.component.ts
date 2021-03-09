import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../ingredient';
import { OptionCategory } from '../option-category';
import { Recipe } from '../recipe';
import {Location} from '@angular/common';
import { RecipeCategory } from '../recipe-category';
import { RecipeServiceService } from '../recipe-service.service';



@Component({
  selector: 'app-new-recipe',
  templateUrl: './new-recipe.component.html',
  styleUrls: ['./new-recipe.component.css']
})
export class NewRecipeComponent implements OnInit {


  model: Recipe = {
    id: 0,
    name: '',
    recipeCategory: RecipeCategory.STARTER,
    ingredients: [],
    instructions: '',
    lastAccessed: '',
    noOfTimesAccessed: 0
  };

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
    this.model.recipeCategory = theCategory;
  }

  constructor(
    private recipeService: RecipeServiceService,
    private location: Location) { }

  ngOnInit() {
  }

  sendRecipe(): void {
    this.recipeService.addRecipe(this.model).subscribe(
      res => {
        this.location.back();
      },
      err => {
        alert('An error has occurred while sending recipe details');
      }
    );
  }

  addToIngredientsList(): void {
    this.model.ingredients.push(JSON.parse(JSON.stringify(this.ingredient)));
  }

}
