import { Ingredient } from "./ingredient";
import { RecipeCategory } from "./recipe-category";

export interface Recipe {
    id: number;
    name: string;
    recipeCategory: RecipeCategory;
    ingredients: Ingredient[];
    instructions: string;
    lastAccessed: string;
    noOfTimesAccessed: number;
  }
