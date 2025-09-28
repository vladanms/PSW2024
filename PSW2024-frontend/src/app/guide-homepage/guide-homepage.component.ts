import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-guide-homepage',
  templateUrl: './guide-homepage.component.html',
  styleUrl: './guide-homepage.component.css'
})
export class GuideHomepageComponent {

constructor(private router: Router, private loginService : LoginService) { }

  	createTour()
  	{
		this.router.navigate(['/createTour']);
	};
	
	viewDrafts()
  	{
		this.router.navigate(['/guideDrafts']);
	};
	
	viewTours()
  	{
		this.router.navigate(['/register']);
	};
	logout(): void 
	{
  	this.loginService.logout().subscribe({
    	next: () => {
      localStorage.removeItem('loggedUser');
      localStorage.removeItem('tourId');
      this.router.navigate(['/login']);
    		}
  		});
	}
}
