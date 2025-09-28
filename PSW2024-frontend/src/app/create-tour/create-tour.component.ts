import { Component, OnInit } from '@angular/core';
import { CreateTourService } from '../service/create-tour.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs/operators'; 
import { ScheduleDTO } from '../dto/ScheduleDTO';

@Component({
  selector: 'app-create-tour',
  templateUrl: './create-tour.component.html',
  styleUrl: './create-tour.component.css'
})
export class CreateTourComponent {
	
  createTourForm: FormGroup;
  errorMessage: string = '';
  image: File | null = null;
  category: string[] = ['Nature', 'Art', 'Sports', 'Shopping', 'Food'];

  constructor(
    private fb: FormBuilder,
    private createTourService: CreateTourService,
    private router: Router
  	)
  	{
    this.createTourForm = this.fb.group({
	  name: ['', [Validators.required]],
	  description: ['', [Validators.required]],
	  category: ['', [Validators.required]],
      difficulty: [1, [Validators.required]],
      price: [0, [Validators.required]],
      time: ['', [Validators.required]]
    });
  }
  
  
  	onSubmit() {
    if (this.createTourForm.invalid) {
      return;
    	}
    	
    	const tour: ScheduleDTO = this.createTourForm.value;
    	
   		
   		this.createTourService.createTour(tour).pipe(
      tap(response => {
          if (response && response.success) {
            console.log("Tour created!");
          } else if (response && response.error) {
            console.log("Error creating tour:", response.error);
          }
      })
    ).subscribe(); 
    }
    
      
    get formControls() 
    {
    return this.createTourForm.controls;
  	}
  
    back()
  	{
    this.router.navigate(['/guideDrafts'])
	}

}
