import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterDTO } from '../dto/registerDTO'
import { MaliciousDTO } from '../dto/MaliciousDTO';
import { tap } from 'rxjs/operators'; 

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
	
	  constructor(private http: HttpClient) { }
	  
	  
	getMaliciousUsers(): Observable<MaliciousDTO[]> {
    return this.http.get<MaliciousDTO[]>(`${this.apiHost}user/getMaliciousUsers`, {withCredentials: true});
  	}
  	
  	banUser(username : String): Observable<void> {
    return this.http.post<void>(`${this.apiHost}user/banUser/${username}`, {withCredentials: true});
  }
}
