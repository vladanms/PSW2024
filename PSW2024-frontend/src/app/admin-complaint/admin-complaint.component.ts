import { Component } from '@angular/core';
import { ComplaintService } from '../service/complaint.service';
import { ComplaintDTO } from '../dto/ComplaintDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-complaint',
  templateUrl: './admin-complaint.component.html',
  styleUrl: './admin-complaint.component.css'
})
export class AdminComplaintComponent {

  complaints : ComplaintDTO[] = [];	

  constructor(private complaintService: ComplaintService, private router: Router) {}

  ngOnInit(): void 
  {
   this.complaintService.getByStatus("ForReview").subscribe({
     next: (complaints) => {
     	this.complaints = complaints;
      },
      error: err => console.error('Error loading complaints', err)
    });
  }
  
  refresh(): void 
  {
    this.complaintService.getByStatus("ForReview").subscribe(complaints => {
      this.complaints = complaints;
	});
  }
  
  onHold(complaint : ComplaintDTO) : void
  {
	  this.complaintService.changeStatus(complaint.id, "OnHold");
	  this.refresh();
  }
  
  reject(complaint : ComplaintDTO) : void
  {
	  this.complaintService.changeStatus(complaint.id, "Rejected");
	  this.refresh();
  }
  
  back()
  {
	  this.router.navigate(['/guideDrafts'])
  }

}
