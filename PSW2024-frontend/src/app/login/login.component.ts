import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  username: string = "";
  password: string = "";
  
    ngOnInit(): void {		
		localStorage.setItem('currentUser', '');
	 }

  
  constructor(private loginService: LoginService, private router: Router, private toastr: ToastrService) { }
  
  
  	login()
  	{
	  	 this.loginService.login(this.username, this.password).subscribe(
      (response) => {
		  console.log(response);
        if (localStorage.getItem('loggedType') === 'Admin') {
            this.router.navigate(['/adminHomepage']);
          } else if (localStorage.getItem('loggedType') === 'Guide') {
            this.router.navigate(['/guideHomepage']);
          } else {
            this.router.navigate(['/registeredHomepage']);
          }
      },
      (error) => {
		  console.log(`${error.message}`);
            alert(error?.error?.error);
      }
    );
     }
	
  	register()
  	{
		this.router.navigate(['/register']);
	};
  }
