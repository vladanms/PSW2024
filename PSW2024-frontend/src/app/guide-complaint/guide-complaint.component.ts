import { Component } from '@angular/core';
import { ComplaintService } from '../service/complaint.service';
import { ComplaintDTO } from '../dto/ComplaintDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guide-complaint',
  templateUrl: './guide-complaint.component.html',
  styleUrl: './guide-complaint.component.css'
})
export class GuideComplaintComponent {
	
  complaints : ComplaintDTO[] = [];	

  constructor(private complaintService: ComplaintService, private router: Router) {}

  ngOnInit(): void 
  {
    const guide = localStorage.getItem('loggedUser');
    if (!guide) {
      alert('Guide not found in localStorage');
      return;
   }

   this.complaintService.getByGuide(guide).subscribe({
     next: (complaints) => {
     	this.complaints = complaints;
      },
      error: err => console.error('Error loading complaints', err)
    });
  }
  
  refresh(): void 
  {
    const guide = localStorage.getItem('guide');
    if (!guide) return;
    this.complaintService.getByGuide(guide).subscribe(complaints => {
      this.complaints = complaints;

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
  
  resolve(complaint : ComplaintDTO) : void
  {
	  this.complaintService.changeStatus(complaint.id, "Resolved");
	  this.refresh();
  }
  
  forReview(complaint : ComplaintDTO) : void
  {
	  this.complaintService.changeStatus(complaint.id, "ForReview");
	  this.refresh();
  }
}
