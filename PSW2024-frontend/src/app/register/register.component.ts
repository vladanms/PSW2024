import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from '../service/register.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterDTO } from '../dto/registerDTO';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm: FormGroup;
  errorMessage: string = '';
  interests: string[] = ['Nature', 'Art', 'Sports', 'Shopping', 'Food'];

  constructor(
    private fb: FormBuilder,
    private registerService: RegisterService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required]],
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]],
      interests: [[], [Validators.required]]
    });
  }

  get formControls() {
    return this.registerForm.controls;
  }
  
   onInterestSelection(event: any) {
    const selectedInterest = event.target.value;
    let interests = this.registerForm.value.interests;
    
	 
      if (event.target.checked) 
      {
      	interests.push(selectedInterest);
      }
      else
      {
      const index = interests.indexOf(selectedInterest);
      	if (index > -1) {
        	interests.splice(index, 1);
      	}
      }
      this.registerForm.patchValue({ interests: interests });
    }
  

  onSubmit() {
    if (this.registerForm.invalid) 
    {
      return;
    }

    const user: RegisterDTO = this.registerForm.value;

    if (user.password !== user.confirmPassword) 
    {
      this.errorMessage = 'Passwords do not match';
      return;
    }

    this.registerService.register(user).subscribe(
      (response) => {
        alert('Registration successful!');
        this.router.navigate(['/login']);
      },
      (error) => {
        this.errorMessage = error.error || 'Registration failed';
      }
    );
  }
	back()
	{
		this.router.navigate(['/login']);
	}
}
