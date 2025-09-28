import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TouristGradeService } from '../service/tourist-grade.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GradeDTO } from '../dto/GradeDTO';

@Component({
  selector: 'app-tourist-grade',
  templateUrl: './tourist-grade.component.html',
  styleUrl: './tourist-grade.component.css'
})
export class TouristGradeComponent implements OnInit{

  gradesOptions = [1, 2, 3, 4, 5];
  selectedGrade: number | null = null;
  description: string = '';
  tourId : string = '';
  tourist : string = ''

constructor(private fb : FormBuilder,private touristGradeService: TouristGradeService, private router: Router) {}

  ngOnInit(): void 
  {
	this.tourId = localStorage.getItem('tourId') || '';
	this.tourist = localStorage.getItem('tourist') || '';
  } 	
 
  isValid(): boolean 
  {
    if (!this.selectedGrade) return false;
    if ((this.selectedGrade === 1 || this.selectedGrade === 2) && this.description.trim() === '') return false;
    return true;
  }

  onSubmit(): void {
 
    const gradeDTO = {
	  tourId: this.tourId,
	  tourist: this.tourist,
      grade: this.selectedGrade || 0,
      content: this.description
    };
    
    if(gradeDTO.grade === 0)
    {
		alert('error')
		return;
	}
    
 	this.touristGradeService.createGrade(gradeDTO).subscribe(
      (response) => {
        alert('Success!');
        this.router.navigate(['/touristTours']);
      },
      (error) => {
        alert('An error occurred');
      });
  }
  
    back()
  	{
		  this.router.navigate(['/touristTours'])
	} 
  }
  
 