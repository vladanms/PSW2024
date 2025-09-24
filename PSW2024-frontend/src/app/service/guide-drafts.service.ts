import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TourDTO } from '../dto/TourDTO';

@Injectable({
  providedIn: 'root'
})
export class GuideDraftsService {
	apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  
  
  constructor(private http: HttpClient) { }
  
  
    getDrafts(guide : String): Observable<TourDTO[]> {
    return this.http.get<TourDTO[]>(`${this.apiHost}post/getDrafts/${guide}'`, {withCredentials: true});
  	}
  
  	publish(id : String): Observable<any>{
	return this.http.get<TourDTO[]>(`${this.apiHost}post/publishTour/${id}`, {withCredentials: true});
	}
	
	delete(id : String): Observable<TourDTO[]>{
	return this.http.get<TourDTO[]>(`${this.apiHost}post/deleteTour/${id}`, {withCredentials: true});	
	}
}
