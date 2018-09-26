import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { User } from '../_models';

@Injectable()
export class CasService {
    constructor(private http: HttpClient) { }

    addCas(user: User){
        return this.http.post(`${environment.apiUrl}/public/users/register`, user, { responseType: 'text'});
    }
	
	getAllCas() {
        return this.http.get<User>(`${environment.apiUrl}/users/getUser`);
    }
	
	countCas() {
        return this.http.get<number>(`${environment.apiUrl}/public/getCasCount`);
    }
	
	countCasTraites() {
        return this.http.get<number>(`${environment.apiUrl}/public/getRslvdCasCount`);
    }
}