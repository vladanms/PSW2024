import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrl: './admin-homepage.component.css'
})
export class AdminHomepageComponent {

 constructor(private router: Router, private loginService : LoginService) {}

	complaints()
	{
		this.router.navigate(['/adminComplaints']);
	}
	
	malicious()
	{
		this.router.navigate(['/adminMalicious']);
	}


	logout()
	{
		this.loginService.logout().subscribe({
    	next: () => {
      localStorage.removeItem('loggedUser');
      this.router.navigate(['/login']);
    		}
  		});
	}


}
