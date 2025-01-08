import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})

export class AdminDashboardComponent implements OnInit {
  stats = {
    totalProducts: 100,
    totalUsers: 500,
    totalOrders: 1000,
    recentOrders: 50
  };

  constructor() { }

  ngOnInit(): void {
    // In the future, we'll fetch real data here
  }
}
