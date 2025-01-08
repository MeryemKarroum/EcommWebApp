import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root', // Makes the service globally available
})
export class CategoryService {
  private http = inject(HttpClient);

  constructor() {}

  // Fetch products by category
  getCategoryName(categoryId: number): Observable<string> {
    return this.http
      .get<{ id: number; category: string }>(`http://localhost:8080/CATEGORY-SERVICE/categories/${categoryId}`)
      .pipe(map(response => response.category)); // Transform response to only return the 'category'
  }

}
