import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TouristInterestsService } from '../service/tourist-interests.service';

@Component({
  selector: 'app-tourist-interests',
  templateUrl: './tourist-interests.component.html',
  styleUrl: './tourist-interests.component.css'
})
export class TouristInterestsComponent {
	
	  interestForm: FormGroup;
	  interests: string[] = ['Nature', 'Art', 'Sports', 'Shopping', 'Food'];
	  
	  constructor(private fb: FormBuilder, private touristInterestsService: TouristInterestsService, private router : Router)
	  {
		this.interestForm = this.fb.group({
				 interests: [[], [Validators.required]]
   		 });
	  }
	  
	get formControls() 
	{
   	 return this.interestForm.controls;
  	}
  
   	onInterestSelection(event: any) 
   	{
    const selectedInterest = event.target.value;
    let interests = this.interestForm.value.interests;
    
	 
      if (event.target.checked) 
      {
      	interests.push(selectedInterest);
      }
      else
      {
      const index = interests.indexOf(selectedInterest);
      	if (index > -1) {
        	interests.splice(index, 1);
      	}
      }
      this.interestForm.patchValue({ interests: interests });
      }	  
	  
	  back()
	  {
		  this.router.navigate(['/registeredHomepage']);
	  }
	  
	  onSubmit()
	  {
		const tourist = localStorage.getItem('loggedUser');
		 const selectedInterests = this.interestForm.value.interests;
		
		if(!tourist)
		{
			return;
		}
	  this.touristInterestsService.setInterests(tourist, selectedInterests).subscribe(
      (response) => {
        alert('Successfully updated!');
      },
      (error) => {
        alert('An error occurred');
      });
	  }
}
