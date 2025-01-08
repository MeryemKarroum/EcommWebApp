import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-admin-navbar',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    NgForOf
  ],
  templateUrl: './admin-navbar.component.html',
  styleUrl: './admin-navbar.component.css'
})
export class AdminNavbarComponent {
  navItems = [
    { path: '/admin', label: 'Dashboard' },
    { path: '/admin/products', label: 'Products' },
    { path: '/admin/users', label: 'Users' },
    { path: '/admin/orders', label: 'Orders' },
  ];
}
