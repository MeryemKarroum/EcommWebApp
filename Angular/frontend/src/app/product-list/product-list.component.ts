import { Component, inject, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {ProductService} from '../product.service';
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
  private http = inject(HttpClient);  // Inject HttpClient
  private route = inject(ActivatedRoute);
  private productService = inject(ProductService);
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const categoryId = params['categoryId'] ?? 5;
      this.category = categoryId;
      this.products$ = this.productService.fetchProductsByCategory(categoryId);

    });
  }

  getProductImage(productId: number): string {
    return this.productService.fetchProductImage(productId);
  }


}
