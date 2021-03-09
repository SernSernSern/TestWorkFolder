import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeServiceService } from '../recipe-service.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  recipe: Recipe;
  instructions: string[] = [];

  constructor(
    private apiService: RecipeServiceService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = +params['id'];
      this.getRecipe();
    });
  }

  getRecipe(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.apiService.getRecipe(id).subscribe(recipe => {
      this.recipe = recipe;
      this.instructions = recipe.instructions.split('\n');
    });
  }

  deleteThisRecipe(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    if (confirm('Are you sure you want to delete this recipe?')) {
      this.apiService.deleteRecipe(id).subscribe(
        res => {
          alert('Recipe deleted successfully');
          this.location.back();
        },
        err => alert('Error occurred while deleting recipe')
      );
    }
  }
}
