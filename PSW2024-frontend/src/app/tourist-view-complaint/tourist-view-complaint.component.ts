import { Component } from '@angular/core';
import { ComplaintService } from '../service/complaint.service';
import { ComplaintDTO } from '../dto/ComplaintDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tourist-view-complaint',
  templateUrl: './tourist-view-complaint.component.html',
  styleUrl: './tourist-view-complaint.component.css'
})
export class TouristViewComplaintComponent {
complaints : ComplaintDTO[] = [];	

  constructor(private complaintService: ComplaintService, private router: Router) {}

  ngOnInit(): void 
  {
    const tourist = localStorage.getItem('loggedUser');
    if (!tourist) {
      alert('Tourist not found in localStorage');
      return;
   }

   this.complaintService.getByTourist(tourist).subscribe({
     next: (complaints) => {
     	this.complaints = complaints;
      },
      error: err => console.error('Error loading complaints', err)
    });
  }
  
  isResolved(complaint: ComplaintDTO): boolean
  {
    return complaint.status === 'Resolved';
  }
  
  isRejected(complaint: ComplaintDTO): boolean 
  {
    return complaint.status === 'Rejected';
  }
  
  isForReview(complaint: ComplaintDTO): boolean 
  {
    return complaint.status === 'ForReview';
  }
  
  isOnHold(complaint: ComplaintDTO): boolean 
  {
  return complaint.status === 'OnHold';
  }
  
  
    back()
  	{
		  this.router.navigate(['/guideHomepage'])
	}
}
