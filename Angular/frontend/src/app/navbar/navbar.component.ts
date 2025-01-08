import { Component, inject, OnInit } from '@angular/core'; // Import CategoryService
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';
import {CartService} from '../service/cart.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.component.html',
  imports: [
    NgForOf,
    AsyncPipe,
    RouterLink,
    NgIf
  ],
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private http = inject(HttpClient);  // Inject HttpClient
  categories: any[] = [];
  cartItemCount$!: Observable<number>;
  constructor(private cartService: CartService) {}
  ngOnInit(): void {
    this.getCategories().subscribe((categories) => {
      this.categories = categories;
    });
    this.cartItemCount$ = this.cartService.cart$.pipe(
      map(items => items.reduce((total, item) => total + item.quantity, 0))
    );
  }

  getCategories():Observable<any[]>{
    // Replace this with your API call
    return this.http.get<any[]>(`http://localhost:8080/CATEGORY-SERVICE/categories`);
  }

}


