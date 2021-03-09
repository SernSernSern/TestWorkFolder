import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Recipe } from './recipe';
import { RecipeCategory } from './recipe-category';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceService {


  private urls = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  addRecipe(recipe: Recipe): Observable<any> {
    const url = `${this.urls}/create`;
    return this.http.post(url, recipe, httpOptions);
  }

  getRecipes(): Observable<Recipe[]> {
    const url = `${this.urls}/all`;
    return this.http.get<Recipe[]>(url, httpOptions);
  }

  getRecipesByNoOfAccesses(): Observable<Recipe[]> {
    const url = `${this.urls}/all/byAccesses`;
    return this.http.get<Recipe[]>(url, httpOptions);
  }

  getRecipesByCategory(recipeCategory: RecipeCategory): Observable<Recipe[]> {
    const url = `${this.urls}/all/byCategory/${recipeCategory}`;
    return this.http.get<Recipe[]>(url, httpOptions);
  }

  getRecipe(id: number): Observable<Recipe> {
    const url = `${this.urls}/recipe/${id}`;
    return this.http.get<Recipe>(url, httpOptions);
  }

  updateRecipe(recipe: Recipe, id: number): Observable<any> {
    const url = `${this.urls}/update/${id}`;
    return this.http.post(url, recipe, httpOptions);
  }

  deleteRecipe(id: number): Observable<any> {
    const url = `${this.urls}/delete/${id}`;
    return this.http.delete(url, httpOptions);
  }

  searchRecipes(term: string): Observable<Recipe[]> {
    const url = `${this.urls}/all/byName/${term}`;
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Recipe[]>(url, httpOptions);
  }
}
