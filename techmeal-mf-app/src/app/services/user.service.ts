import { AppConstant } from '../common/appconstant';
import { RegistrationData } from '../common/dto/registrationdata';
import { User } from '../common/dto/user';
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
  constructor(private http: HttpClient, private authService: AuthService) { }

  createUser(userData): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post(AppConstant.CREATE_USER_URL, JSON.stringify(userData), httpOptions)
      .map((response: HttpResponse<RegistrationData>) => {
        if (response) {
          return true;
        } else {
          return false;
        }
      }).catch((e) => {
        this.error = e;
        return Observable.of(e);
      });
  }

  editUser(userData): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post(AppConstant.EDIT_USER_URL, JSON.stringify(userData), httpOptions)
      .map((response: HttpResponse<RegistrationData>) => {
        if (response) {
          return true;
        } else {
          return false;
        }
      }).catch((e) => {
        this.error = e;
        return Observable.of(e);
      });
  }

   getUsers(): Observable<Object> {
      return this.http.get(AppConstant.GET_ALL_USERS_URL)
        .map((response: HttpResponse<Array<User>>) => {
          if (response) {
            return response;
          } else {
            return null;
          }
        }).catch((e) => {
          this.error = e;
          return Observable.of(e);
        });
  }
  
  deleteUser(userData): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put(AppConstant.DELETE_USER_URL, JSON.stringify(userData), httpOptions)
      .map((response: HttpResponse<RegistrationData>) => {
        if (response) {
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
