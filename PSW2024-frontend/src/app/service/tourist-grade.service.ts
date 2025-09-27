import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GradeDTO } from '../dto/GradeDTO';

@Injectable({
  providedIn: 'root'
})
export class TouristGradeService {
apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json',  'Accept': 'application/json' });
  	  constructor(private http: HttpClient) { }
	  
	  createGrade(gradeDTO: GradeDTO): Observable<any> {	
		  let grade = {
			tourId : gradeDTO.tourId,
			tourist : gradeDTO.tourist,
			content : gradeDTO.content,
			grade : gradeDTO.grade		
		};
		  	
		return this.http.post<any>(`${this.apiHost}tour/gradeTour`, grade, {withCredentials: true});
	
	}
}
