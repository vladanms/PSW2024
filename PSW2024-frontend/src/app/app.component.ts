import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'PSW2024-frontend'
  constructor(private router: Router){}
  
  public home()
  {
	  this.router.navigate(['/login']);
  }
}
