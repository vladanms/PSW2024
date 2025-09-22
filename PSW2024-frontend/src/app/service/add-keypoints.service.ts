import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddKeypointsService {
 	  apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json',  'Accept': 'application/json' });
	
	  constructor(private http: HttpClient) { }
	  
	  addKeypoint(formData: FormData): Observable<any> {		
		return this.http.post<any>(`${this.apiHost}tour/addKeypoint`, formData, {withCredentials: true});
	
	}
}
