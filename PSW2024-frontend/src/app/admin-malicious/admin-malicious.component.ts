import { Component } from '@angular/core';
import { UserService } from '../service/user.service';
import { MaliciousDTO } from '../dto/MaliciousDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-malicious',
  templateUrl: './admin-malicious.component.html',
  styleUrl: './admin-malicious.component.css'
})
export class AdminMaliciousComponent {
	
 users : MaliciousDTO[] = [];

 constructor(private userService: UserService, private router: Router) {}

 ngOnInit(): void 
  {
   this.userService.getMaliciousUsers().subscribe({
     next: (users) => {
     	this.users = users;
      },
      error: err => console.error('Error loading users', err)
    });
  }
  
  refresh()
  {
	  this.userService.getMaliciousUsers().subscribe({
     next: (users) => {
     	this.users = users;
      }
    });
  }  
banUser(user: MaliciousDTO) {
  this.userService.banUser(user.username).subscribe({
    next: () => {
	  if(!user.isBanned)
	  {
		  alert('User banned.');
	  }
	  else
	  {
		  alert('User unbanned.');
	  }
      this.refresh();
    }
  });
}
  
  back()
  {
	  this.router.navigate(['/adminHomepage'])
  }
}
