import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Recipe } from '../recipe';
import { RecipeServiceService } from '../recipe-service.service';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';


@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {

  recipes$: Observable<Recipe[]>;
  private searchTerms = new Subject<string>();

  constructor(private apiService: RecipeServiceService) { }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.recipes$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.apiService.searchRecipes(term)),
    );
  }

}
