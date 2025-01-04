import { Component, inject, OnInit } from '@angular/core'; // Import CategoryService
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AsyncPipe, NgForOf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.component.html',
  imports: [
    NgForOf,
    AsyncPipe,
    RouterLink
  ],
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private http = inject(HttpClient);  // Inject HttpClient
  categories: any[] = [];
  ngOnInit(): void {
    this.getCategories().subscribe((categories) => {
      this.categories = categories;
    });
  }

  getCategories():Observable<any[]>{
    // Replace this with your API call
    return this.http.get<any[]>(`http://localhost:8080/CATEGORY-SERVICE/categories`);
  }

}


