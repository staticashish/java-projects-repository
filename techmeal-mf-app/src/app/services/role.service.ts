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

    const createRoleUrl = 'https://techmeal-mf-gateway-service.herokuapp.com/gateway/db/role/create';
    // const tokenUrl = 'http://localhost:8005/gateway/db/user/create';

    return this.http.post(createRoleUrl, JSON.stringify(roleData), httpOptions)
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
    const createRoleUrl = 'https://techmeal-mf-gateway-service.herokuapp.com/gateway/db/role/all';
    // const tokenUrl = 'http://localhost:8005/gateway/db/user/create';

    return this.http.get(createRoleUrl)
      .map((response: HttpResponse<Array<{id: number, name: string}>>) => {
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
