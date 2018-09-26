import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

import { environment } from '../../environments/environment';

@Injectable()
export class MediaService {

	constructor(private http: HttpClient) { }
 
	getFile(filename: any) {
	return this.http.get<any>(`${environment.apiUrl}/public/media/getFile/` + filename);
	}
}