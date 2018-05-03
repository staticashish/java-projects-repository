import { HttpHeaders } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import 'rxjs/Rx';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient, private router: Router) { }

  obtainAccessToken(loginData) {
    const params = new URLSearchParams();
    params.set('username', loginData.username);
    params.set('password', loginData.password);
    params.set('grant_type', 'password');
    params.set('client_id', 'client');

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/x-www-form-urlencoded; charset=utf-8',
        'Authorization': 'Basic ' + btoa('client:client')
    })
    };

  const tokenUrl = 'http://techmeal-mf-gateway-service.herokuapp.com/gateway/auth/oauth/token';
    // const tokenUrl = 'http://techmeal-mf-auth-server.herokuapp.com/auth/oauth/token';
     this.http.post(tokenUrl, params.toString(), httpOptions )
      .subscribe(
        data => console.log(data),
        err => alert('Invalid Credentials' + JSON.stringify(err)));

  }
}
