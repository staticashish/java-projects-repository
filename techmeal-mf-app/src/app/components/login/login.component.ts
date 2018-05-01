import { AuthService } from '../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loading = false;
  public loginData = {username: "", password: ""};
  constructor(private _authService:AuthService) { }

  ngOnInit() {
  }

  login() {
        this.loading = true;
        this._authService.obtainAccessToken(this.loginData);
    this.loading = false;
    }
}
