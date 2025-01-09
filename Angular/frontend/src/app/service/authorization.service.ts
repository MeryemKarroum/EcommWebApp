// Angular/frontend/src/app/service/authorization.service.ts
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthorizationService {
  private roles: string[] = [];

  constructor(private http: HttpClient) {}

  // Fetch roles from the backend
  fetchRoles(): Observable<string[]> {
    return this.http.get<{ roles: string[] }>('http://localhost:8089/api/auth/roles').pipe(
      map(response => response.roles || [])
    );
  }

  // Set roles locally
  setRoles(roles: string[]): void {
    this.roles = roles;
  }

  // Check if a user has a specific role
  hasRole(role: string): boolean {
    return this.roles.includes(role);
  }

  // Clear roles (e.g., on logout)
  clearRoles(): void {
    this.roles = [];
  }

  // Utility to check if the user has any roles
  hasAnyRole(): boolean {
    return this.roles.length > 0;
  }
}
