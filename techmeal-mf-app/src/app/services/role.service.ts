import { AppConstant } from '../common/appconstant';
import { Role } from '../common/dto/role';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpResponse } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class RoleService {

  public error: HttpErrorResponse;

  constructor(private http: HttpClient) { }

  createRole(roleData): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    // const tokenUrl = 'http://localhost:8005/gateway/db/user/create';

    return this.http.post(AppConstant.CREATE_ROLE_URL, JSON.stringify(roleData), httpOptions)
      .map((response: HttpResponse<Object>) => {
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


  getRoles(): Observable<Object> {
    // const tokenUrl = 'http://localhost:8005/gateway/db/user/create';

    return this.http.get(AppConstant.GET_ALL_ROLES_URL)
      .map((response: HttpResponse<Array<Role>>) => {
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

}
