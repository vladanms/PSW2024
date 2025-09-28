import { CanActivate,ActivatedRouteSnapshot, Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate  {
   
   constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data['role']; 
    const userRole = localStorage.getItem('loggedType');

    if (!userRole || userRole !== expectedRole) {
      if(!userRole)
      {
		  this.router.navigate(['/login'])
	  }
	  if(userRole === 'Tourist')
      {
		  this.router.navigate(['/touristHomepage'])
	  }
	  if(userRole === 'Guide')
      {
		  this.router.navigate(['/guideHomepage'])
	  }
	  if(userRole === 'Tourist')
      {
		  this.router.navigate(['/registeredHomepage'])
	  }
      return false;
    }

    return true;
  }
};
