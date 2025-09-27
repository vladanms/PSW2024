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
    private fb: FormBuilder,private createTourService: TouristComplaintService, private router: Router	)
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
   	this.createTourService.createComplaint(complaint);
    }
    
      
    get formControls() 
    {
    return this.createComplaintForm.controls;
  	} 
 }
