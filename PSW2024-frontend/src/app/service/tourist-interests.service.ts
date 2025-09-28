import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TouristInterestsService {
	apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
	
	  constructor(private http: HttpClient) { }
	  
	   setInterests(tourist : String, interests: String[]): Observable<any> {
	
		return this.http.post<any>(`${this.apiHost}user/setInterests/${tourist}/${interests}`, interests, {headers: this.headers, withCredentials: true });
	}

}
