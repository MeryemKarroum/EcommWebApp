import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product.module'; // Assuming Product model exists

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/PRODUCT-SERVICE/products';
  private categoryUrl = 'http://localhost:8080/CATEGORY-SERVICE/categories';

  constructor(private http: HttpClient) {}

  // Get all categories
  getCategories(): Observable<any[]> {
    return this.http.get<any[]>(this.categoryUrl);
  }

  getProducts(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }
  // Add a product
  addProduct(product: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/add`, product);
  }

  // Update a product
  updateProduct(productId: string, product: FormData): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${productId}`, product);
  }

  // Get a product by ID for editing
  getProductById(productId: string): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${productId}`);
  }
}
