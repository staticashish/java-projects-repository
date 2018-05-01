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
    const params = new HttpParams();
    params.set('username', loginData.username);
    params.set('password', loginData.password);
    params.set('grant_type', 'password');
    params.set('client_id', 'client');
    const headers = new HttpHeaders();
    headers.set('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
    headers.set('Authorization', 'Basic ' + btoa('client:client'));

   const tokenUrl = 'http://localhost:8005/gateway/auth/oauth/token';
    // const tokenUrl = 'http://techmeal-mf-auth-server.herokuapp.com/auth/oauth/token';
     this.http.post(tokenUrl, params.toString(), { headers : headers } )
      .subscribe(
        data => console.log(data),
        err => alert('Invalid Credentials' + JSON.stringify(err)));

  }
}
