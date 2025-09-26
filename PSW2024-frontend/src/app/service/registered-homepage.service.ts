import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TourDTO } from '../dto/TourDTO';

@Injectable({
  providedIn: 'root'
})
export class RegisteredHomepageService {
	apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  
  
  constructor(private http: HttpClient) { }
  
    getAvailable(tourist : String): Observable<TourDTO[]> {
    return this.http.get<TourDTO[]>(`${this.apiHost}tour/getAvailable/${tourist}`, {withCredentials: true});
  	}
}
