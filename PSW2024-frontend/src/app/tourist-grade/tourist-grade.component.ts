import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TouristGradeService } from '../service/tourist-grade.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GradeDTO } from '../dto/GradeDTO';
@Component({
  selector: 'app-tourist-grade',
  templateUrl: './tourist-grade.component.html',
  styleUrl: './tourist-grade.component.css'
})
export class TouristGradeComponent {

  createGradeForm: FormGroup;

  gradesOptions = [1, 2, 3, 4, 5];

  constructor( private fb: FormBuilder,private touristGradeService: TouristGradeService,private router: Router ) 
  {
    this.createGradeForm = this.fb.group({
      grade: [null, [Validators.required]],
      content: ['']
    });
      this.createGradeForm.get('grade')?.valueChanges.subscribe(value => {
      this.setContentValidators(value);
    });
    
  }

   private setContentValidators(gradeValue: number)
   {	  
    	const contentControl = this.createGradeForm.get('content');

   		if (gradeValue === 1 || gradeValue === 2)
		{
      		contentControl?.setValidators([Validators.required]);
    	} 
    	else
		{
			contentControl?.clearValidators();
		}

    contentControl?.updateValueAndValidity();
  }
}