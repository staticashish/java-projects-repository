import { AuthService } from './services/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public title = 'Techmeal Mutual fund Application';
  public username: string;
  public userRole: string;
  constructor(private authService: AuthService) {}

  isAuthenticated() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.token) {
      this.username = currentUser.username;
      this.userRole = currentUser.role;
      return true;
    } else {
      return false;
    }
  }

  logout() {
    this.authService.logout();
  }
}
