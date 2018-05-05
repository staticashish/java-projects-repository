import {HttpErrorResponse} from '@angular/common/http';
import {HttpResponse} from '@angular/common/http';
import {HttpHeaders} from '@angular/common/http';
import {HttpParams} from '@angular/common/http';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Rx';
import 'rxjs/Rx';
import * as jwt_decode from 'jwt-decode';

@Injectable()
export class AuthService {

  loading = false;
  constructor(private http: HttpClient, private router: Router) {}
  error: HttpErrorResponse;

  obtainAccessToken(loginData): Observable<Object> {
    const params = new URLSearchParams();
    params.set('username', loginData.username);
    params.set('password', loginData.password);
    params.set('grant_type', 'password');
    params.set('client_id', 'client');

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
        'Authorization': 'Basic ' + btoa('client:client')
      })
    };

    const tokenUrl = 'http://techmeal-mf-gateway-service.herokuapp.com/gateway/auth/oauth/token';
    // const tokenUrl = 'http://localhost:8005/gateway/auth/oauth/token';

    return this.http.post(tokenUrl, params.toString(), httpOptions)
      .map((response: HttpResponse<Object>) => {
        if (response) {
          this.saveToken(response, loginData.username);
          return true;
        } else {
          return false;
        }
      }).catch((e) => {
        this.error = e;
        return Observable.of(e);
      });
  }

  saveToken(token, username) {
    const tokenInfo = this.getDecodedAccessToken(token.access_token);
    localStorage.setItem('currentUser', JSON.stringify({username: username, token: token.access_token, role: tokenInfo.authorities[0]}));
    console.log(localStorage.getItem('currentUser'));
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (error) {
      return null;
    }
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['/login']);
  }

}
