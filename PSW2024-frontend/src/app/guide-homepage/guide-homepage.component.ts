import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guide-homepage',
  templateUrl: './guide-homepage.component.html',
  styleUrl: './guide-homepage.component.css'
})
export class GuideHomepageComponent {

constructor(private router: Router) { }

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

}
