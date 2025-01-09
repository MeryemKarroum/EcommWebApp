import { Component, inject, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { AsyncPipe, NgForOf, NgIf } from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import { CartService } from '../service/cart.service';
import { AuthorizationService } from '../service/authorization.service';

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
  private http = inject(HttpClient);
  categories: any[] = [];
  cartItemCount$!: Observable<number>;
  isClient: boolean = false;

  constructor(
    private router: Router,
    private cartService: CartService,
    private authorizationService: AuthorizationService
  ) {}

  ngOnInit(): void {
    this.checkRoles();
    this.getCategories().subscribe((categories) => {
      this.categories = categories;
    });
    this.cartItemCount$ = this.cartService.cart$.pipe(
      map(items => items.reduce((total, item) => total + item.quantity, 0))
    );
    this.isClient = this.authorizationService.hasRole('CLIENT');
  }

  getCategories(): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/CATEGORY-SERVICE/categories`);
  }

  logout(): void {
    localStorage.removeItem('jwt'); // Remove JWT from localStorage
    this.router.navigate(['/login']); // Redirect to login page
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('jwt'); // Check if JWT exists
  }
  checkRoles(): void {
    this.isClient = this.authorizationService.hasRole('CLIENT');
  }
}
