import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TouristComplaintService } from '../service/tourist-complaint.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ComplaintDTO } from '../dto/ComplaintDTO';

@Component({
  selector: 'app-tourist-complaint',
  templateUrl: './tourist-complaint.component.html',
  styleUrl: './tourist-complaint.component.css'
})
export class TouristComplaintComponent {

  createComplaintForm: FormGroup;
  image: File | null = null;

  constructor(
    private fb: FormBuilder,private touristComplaintService: TouristComplaintService, private router: Router	)
  	{
    this.createComplaintForm = this.fb.group({
	  name: ['', [Validators.required]],
	  description: ['', [Validators.required]],
    });
  }
  
  
 	onSubmit() 
 	{
    	if (this.createComplaintForm.invalid) 
   		{
      		return;
   		}
    	
    const complaint: ComplaintDTO = this.createComplaintForm.value;
  	const tourist = localStorage.getItem('loggedUser');
  	const tourId = localStorage.getItem('tourId');
  	const guide = localStorage.getItem('guide');

  	complaint.tourist = tourist || '';
  	complaint.tourId = tourId || '';
  	complaint.guide = guide || '';
    
      this.touristComplaintService.createComplaint(complaint).subscribe(
      (response) => {
        alert('Success!');
        this.router.navigate(['/touristTours']);
      },
      (error) => {
        alert('An error occurred');
      });
   
    }
    
      
    get formControls() 
    {
    return this.createComplaintForm.controls;
  	} 
  	
    back()
  	{
		  this.router.navigate(['/touristTours'])
	}

 }
