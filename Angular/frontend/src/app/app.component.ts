import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavbarComponent} from './navbar/navbar.component';
import {HomeComponent} from './home/home.component';
import {ProductListComponent} from './product-list/product-list.component';
import {CartComponent} from './cart/cart.component';
import {AdminNavbarComponent} from './admin/admin-navbar/admin-navbar.component';
import {AdminDashboardComponent} from './admin/admin-dashboard/admin-dashboard.component';
import {NgIf} from '@angular/common';
import { Router } from '@angular/router';
import {ProductAdminlistComponent} from './admin/product-adminlist/product-adminlist.component';
import {OrderListComponent} from './admin/order-list/order-list.component';
import {ProductFormComponent} from './admin/product-form/product-form.component';
import {UserListComponent} from './admin/user-list/user-list.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, HomeComponent, ProductListComponent, CartComponent, AdminNavbarComponent, AdminDashboardComponent, NgIf,ProductAdminlistComponent,OrderListComponent,ProductFormComponent,UserListComponent,LoginComponent,RegisterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  constructor(private router: Router) {}
  isClient(): boolean {
    // If the URL starts with '/admin', show the admin navbar
    return !this.router.url.startsWith('/admin');
  }
}
