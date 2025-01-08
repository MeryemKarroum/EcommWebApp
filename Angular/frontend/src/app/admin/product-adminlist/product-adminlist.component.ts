import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import {CurrencyPipe, NgForOf, NgIf} from '@angular/common';
import { ProductService } from '../service/product.service';
import { Product } from '../models/product.module';

@Component({
  selector: 'app-product-adminlist',
  standalone: true,
  imports: [
    RouterLink,
    CurrencyPipe,
    NgForOf,
    NgIf
  ],
  templateUrl: './product-adminlist.component.html',
  styleUrls: ['./product-adminlist.component.css']
})
export class ProductAdminlistComponent implements OnInit {
  products: Product[] = [];
  currentPage: number = 1;
  totalPages: number = 1;
  pageSize: number = 10; // Nombre de produits par page

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts(this.currentPage - 1, this.pageSize).subscribe({
      next: (data) => {
        console.log('Full response:', data); // Log the entire response
        if (data && Array.isArray(data.content)) {
          this.products = data.content;
          if (data.totalPages !== undefined) {
            this.totalPages = data.totalPages; // Accéder à totalPages directement
          } else {
            console.error('totalPages is undefined in the response:', data);
          }
        } else {
          console.error('Unexpected response format:', data);
          this.products = [];
        }
        console.log('Products loaded successfully:', this.products);
      },
      error: (err) => {
        console.error('Error loading products:', err);
      },
    });
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.loadProducts();
    }
  }

  deleteProduct(id: number) {
    // Implémenter la logique de suppression ici
    console.log(`Deleting product with id ${id}`);
  }
}
