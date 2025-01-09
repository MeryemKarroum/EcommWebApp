import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import {Router, RouterLink} from '@angular/router';
import { AuthorizationService } from '../service/authorization.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private authorizationService: AuthorizationService
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.userService.login(this.loginForm.value.username, this.loginForm.value.password)
        .subscribe(token => {
          localStorage.setItem('jwt', token);
          const decodedToken = this.decodeToken(token);
          this.authorizationService.setRoles(decodedToken.roles);
          if (decodedToken.roles.includes('ADMIN')) {
            this.router.navigate(['/admin']);
          } else {
            this.router.navigate(['/']);
          }
        }, error => {
          console.error('Login failed', error);
        });
    }
  }

  // Helper method to decode JWT token
  decodeToken(token: string): any {
    const payload = token.split('.')[1];
    return JSON.parse(atob(payload));
  }
}
