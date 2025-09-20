import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterDTO } from '../dto/registerDTO';
import { tap } from 'rxjs/operators'; 


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
	
	  constructor(private http: HttpClient) { }
	  
	  register(registerDTO: RegisterDTO): Observable<any> {
		
		let user = {
		username: registerDTO.username,
		password : registerDTO.password,
		email : registerDTO.email,
		name : registerDTO.name,
		surname : registerDTO.surname,
		};
		
		return this.http.post<any>(`${this.apiHost}user/register`, user, {headers: this.headers, withCredentials: true }).pipe(
		  tap(response => {
          if (response && response.registered) {
            console.log("Succesfully regisetred:", response.registered);
          } else if (response && response.error) {
            console.log("Error during registration:", response.error);
          }
        })
      );
	
	}

}
