import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Constants } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): Observable<any> {
    const body = new HttpParams().set('username', username).set('password', password)

    return this.http.post(Constants.SERVER_URL + 'main', body, {'responseType' : 'text'})
    .pipe(
      map(response => { 
        Constants.CHECK_LOGIN = true
      }),
      catchError((err: any | HttpErrorResponse) => {
        return throwError(err);
      })
     )
  }
}
