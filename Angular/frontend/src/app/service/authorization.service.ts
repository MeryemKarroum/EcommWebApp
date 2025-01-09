// Angular/frontend/src/app/service/authorization.service.ts
import { Injectable } from '@angular/core';
import {map, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  constructor(private http: HttpClient) {}
  private roles: string[] = [];
  fetchRoles(): Observable<string[]> {
    return this.http.get<any>('http://localhost:8089/api/auth/roles').pipe(
      map(response => response.roles)
    );
  }
  setRoles(roles: string[]) {
    this.roles = roles;
  }

  hasRole(role: string): boolean {
    return this.roles.includes(role);
  }
}
