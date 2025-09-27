import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ComplaintDTO } from '../dto/ComplaintDTO';

@Injectable({
  providedIn: 'root'
})
export class TouristComplaintService {
 apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json',  'Accept': 'application/json' });
  	  constructor(private http: HttpClient) { }
	  
	  createComplaint(complaintDTO: ComplaintDTO): Observable<any> {	
		  let complaint = {
			tourist : complaintDTO.tourist,
			guide : complaintDTO.guide,
			tourId : complaintDTO.tourId,
		  	name : complaintDTO.name,
		  	description : complaintDTO.description			
		};
		  	
		return this.http.post<any>(`${this.apiHost}complaint/createComplaint`, complaint, {withCredentials: true});
	
	}
}
