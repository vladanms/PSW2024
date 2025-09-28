import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ComplaintDTO } from '../dto/ComplaintDTO';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {
	  apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json',  'Accept': 'application/json' });
  	  constructor(private http: HttpClient) { }
  	  
  getByGuide(guide: string): Observable<ComplaintDTO[]> {
    return this.http.get<ComplaintDTO[]>(`${this.apiHost}complaint/getByGuide/${guide}`,  { withCredentials: true });
  }
  
    getByTourist(tourist: string): Observable<ComplaintDTO[]> {
    return this.http.get<ComplaintDTO[]>(`${this.apiHost}complaint/getByTourist/${tourist}`, { withCredentials: true });
  }

  getByStatus(status: string): Observable<ComplaintDTO[]> {
    return this.http.get<ComplaintDTO[]>(`${this.apiHost}complaint/getByStatus/${status}`, { withCredentials: true });
  }
  
   changeStatus(complaintId: string, status: string): Observable<void> {
    return this.http.post<void>(`${this.apiHost}complaint/updateStatus/${complaintId}/${status}`, {},  { withCredentials: true });
  }

}
