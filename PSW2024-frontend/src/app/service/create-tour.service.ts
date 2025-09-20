import { ChangeDetectionStrategy, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScheduleDTO } from '../dto/ScheduleDTO';


@Injectable({
  providedIn: 'root'
})
export class CreateTourService {
 	  apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json',  'Accept': 'application/json' });
  	  constructor(private http: HttpClient) { }
	  
	  createTour(scheduleDTO: ScheduleDTO): Observable<any> {	
		  let tour = {
		  	name : scheduleDTO.name,
    		description : scheduleDTO.description,
   			category : scheduleDTO.category,
    		difficulty : scheduleDTO.difficulty,
    		price : scheduleDTO.price,
    		time : scheduleDTO.time,
    		guideName : localStorage.getItem('loggedUser')
		};
		  	
		return this.http.post<any>(`${this.apiHost}tour/createTour`, tour, {withCredentials: true});
	
	}
}

