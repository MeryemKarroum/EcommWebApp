import { Component, inject, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '../service/product.service';
import {CategoryService} from '../service/category.service'
import {CartService} from '../service/cart.service';
@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    AsyncPipe,
    NgForOf,
    NgIf
  ],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products$!: Observable<any[]>;  // Declare it as an Observable
  category:any;
  categoryName!:Observable<string>;
  private http = inject(HttpClient);  // Inject HttpClient
  private route = inject(ActivatedRoute);
  private productService = inject(ProductService);
  private categoryService = inject(CategoryService);
  private cartService = inject(CartService);

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const categoryId = params['categoryId'] ?? 5;
      this.category = categoryId;
      this.products$ = this.productService.fetchProductsByCategory(categoryId);
      this.categoryName=this.categoryService.getCategoryName(categoryId);
    });
  }

  getProductImage(productId: number): string {
    return this.productService.fetchProductImage(productId);
  }

  addToCart(product: any) {
    this.cartService.addToCart({
      id: product.id,
      name: product.name,
      price: product.price,
      quantity: 1,
      image: this.getProductImage(product.id)
    });
  }
}
