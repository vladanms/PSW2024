import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RegisteredHomepageComponent } from './registered-homepage/registered-homepage.component';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { GuideHomepageComponent } from './guide-homepage/guide-homepage.component';

const routes: Routes = [
		{path: 'login', component : LoginComponent},
		{path: 'register', component : RegisterComponent},
		{path: 'registeredHomepage', component : RegisteredHomepageComponent},
		{path: 'adminHomepage', component : AdminHomepageComponent},
		{path: 'guideHomepage', component : GuideHomepageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
