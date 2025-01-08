import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // Makes the service globally available
})
export class ProductService {
  private http = inject(HttpClient);

  constructor() {}

  // Fetch products by category
  fetchProductsByCategory(categoryId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/PRODUCT-SERVICE/products/category/${categoryId}`);
  }

  // Fetch image for a specific product
  fetchProductImage(productId: number): string {
    return `http://localhost:8080/PRODUCT-SERVICE/products/imageProduct/${productId}`;
  }
}
