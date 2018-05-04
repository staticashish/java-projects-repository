import { AuthService } from './services/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Techmeal Mutual fund Application';
  username: String;
  constructor(private authService: AuthService) {}

  isAuthenticated() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.token) {
      this.username = currentUser.username;
      return true;
    } else {
      return false;
    }
  }

  logout() {
    this.authService.logout();
  }
}
