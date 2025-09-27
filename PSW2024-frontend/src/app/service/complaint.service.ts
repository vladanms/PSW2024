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
  	  
  getByGuide(guideUsername: string): Observable<ComplaintDTO[]> {
    return this.http.get<ComplaintDTO[]>(`${this.apiHost}/complaints.getByGuide/${guideUsername}`);
  }

  getByStatus(status: string): Observable<ComplaintDTO[]> {
    return this.http.get<ComplaintDTO[]>(`${this.apiHost}/complaints.getByStatus/${status}`);
  }
  
   changeStatus(complaintId: string, status: string): Observable<void> {
    return this.http.put<void>(`${this.apiHost}/complaints.updateStatus/${complaintId}/${status}`, {});
  }

}
