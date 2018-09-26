import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from '../_models';

import { environment } from '../../environments/environment';

@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(user: User) {
        return this.http.post(`${environment.apiUrl}/public/users/login`, user,  { responseType: 'text'});
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('token');
    }
}