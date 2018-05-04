import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loading = false;
  error: string;
  public loginData = {username: '', password: ''};
  public errorResponse: HttpErrorResponse;
  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
  }

  login() {
      this.loading = true;
      this.authService.obtainAccessToken(this.loginData)
        .subscribe(result => {
         if (result === true) {
           this.router.navigate(['/']);
           this.loading = false;
         } else {
           this.errorResponse = this.authService.error;
           if (this.errorResponse.status === 401 || this.errorResponse.status === 400) {
             this.error = 'Invalid username or password';
           } else {
             this.error = 'Service is temporary unavailable. Please try again later !!!';
           }
           this.loading = false;
         }
        });
  }

  logout() {
      this.authService.logout();
  }
}
