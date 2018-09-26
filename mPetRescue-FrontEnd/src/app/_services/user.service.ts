import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { User } from '../_models';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }

    register(user: User){
        return this.http.post(`${environment.apiUrl}/public/users/register`, user, { responseType: 'text'});
    }
	
	getCurrentUser() {
        return this.http.get<User>(`${environment.apiUrl}/public/users/getUser`);
    }

    update(user: User) {
        return this.http.put(`${environment.apiUrl}/public/users/` + user.id, user);
    }
	
	countUsers() {
        return this.http.get<number>(`${environment.apiUrl}/public/getUsersCount`);
    }
	
	getTopUsers() {
        return this.http.get<User[]>(`${environment.apiUrl}/public/getTopUsers`);
    }
}