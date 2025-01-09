import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthorizationService} from '../../service/authorization.service';

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
  constructor(
    private router: Router,
    private authorizationService: AuthorizationService
  ) {}

  ngOnInit(): void {
    this.checkAdminRole();
  }

  checkAdminRole() {
    if (!this.authorizationService.hasRole('ADMIN')) {
      console.error('Access denied');
      this.router.navigate(['/access-denied']);
    }
  }
}
