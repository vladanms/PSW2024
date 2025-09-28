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
    return this.http.get<TourDTO[]>(`${this.apiHost}tour/getAllByGuide/${guide}`, {withCredentials: true});
  	}
  
  	publish(tourId : String): Observable<any>{
	return this.http.post<TourDTO[]>(`${this.apiHost}tour/publishTour/${tourId}`, {}, {withCredentials: true });
	}
	
	delete(guideName : String, tourId : String): Observable<TourDTO[]>{
	return this.http.delete<TourDTO[]>(`${this.apiHost}tour/deleteTour/${guideName}/${tourId}`, {withCredentials: true});	
	}
}
