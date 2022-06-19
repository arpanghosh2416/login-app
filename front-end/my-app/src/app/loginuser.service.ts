import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { User } from './user';

@Injectable({
  providedIn: 'root'
})

export class LoginuserService {

  private loginApi = 'http://localhost:8888/user-api/login';

  constructor(private httpClient: HttpClient) {
  }

  loginUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.loginApi, user);
  }

}