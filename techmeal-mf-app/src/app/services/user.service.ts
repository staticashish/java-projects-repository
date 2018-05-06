import { AuthService } from './auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpResponse } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  public error: HttpErrorResponse;
  constructor(private http: HttpClient, private authService:AuthService) { }

  createUser(registerData): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    const tokenUrl = 'https://techmeal-mf-gateway-service.herokuapp.com/gateway/db/user/create';
    // const tokenUrl = 'http://localhost:8005/gateway/db/user/create';

    return this.http.post(tokenUrl, JSON.stringify(registerData), httpOptions)
      .map((response: HttpResponse<Object>) => {
        if (response) {
          console.log(response.body);
          return true;
        } else {
          return false;
        }
      }).catch((e) => {
        this.error = e;
        return Observable.of(e);
      });
  }

}
