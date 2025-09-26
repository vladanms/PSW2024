import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TourDTO } from '../dto/TourDTO';


@Injectable({
  providedIn: 'root'
})
export class TouristCheckoutService {

  apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  
  
  constructor(private http: HttpClient) { }
  
    getRewardPoints(tourist : String): Observable<number> {
    return this.http.get<number>(`${this.apiHost}user/getRewardPoints/${tourist}`, {withCredentials: true});
  	}
  	
  	reserveTours(touristName: string, usePoints: boolean, tourIds: string[]): Observable<string> {
    return this.http.post<string>(`${this.apiHost}/reserve/${touristName}/${usePoints}`, tourIds);
  }
}
