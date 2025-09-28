import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RegisteredHomepageComponent } from './registered-homepage/registered-homepage.component';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { GuideHomepageComponent } from './guide-homepage/guide-homepage.component';
import { AddKeypointsComponent } from './add-keypoints/add-keypoints.component';
import { CreateTourComponent } from './create-tour/create-tour.component';
import { GuideDraftsComponent } from './guide-drafts/guide-drafts.component'
import { GuideComplaintComponent } from './guide-complaint/guide-complaint.component';
import { TouristCheckoutComponent } from './tourist-checkout/tourist-checkout.component';
import { TouristComplaintComponent } from './tourist-complaint/tourist-complaint.component';
import { TouristGradeComponent } from './tourist-grade/tourist-grade.component';
import { AdminComplaintComponent } from './admin-complaint/admin-complaint.component';
import { TouristToursComponent } from './tourist-tours/tourist-tours.component';
import { TouristInterestsComponent } from './tourist-interests/tourist-interests.component';
import { AdminMaliciousComponent } from './admin-malicious/admin-malicious.component';
import { TouristViewComplaintComponent } from './tourist-view-complaint/tourist-view-complaint.component';
import { AuthGuard } from './guard/auth.guard';

const routes: Routes = [
		{path: 'login', component : LoginComponent},
		{path: 'register', component : RegisterComponent},
		{path: 'registeredHomepage', component : RegisteredHomepageComponent, canActivate: [AuthGuard], data: { role: 'Tourist' }},
		{path: 'adminHomepage', component : AdminHomepageComponent, canActivate: [AuthGuard], data: { role: 'Admin' }},
		{path: 'guideHomepage', component : GuideHomepageComponent, canActivate: [AuthGuard], data: { role: 'Guide' }},
		{path: 'addKeypoints', component : AddKeypointsComponent, canActivate: [AuthGuard], data: { role: 'Guide' }},
		{path :'createTour', component : CreateTourComponent, canActivate: [AuthGuard], data: { role: 'Guide' }},
		{path :'guideDrafts', component : GuideDraftsComponent, canActivate: [AuthGuard], data: { role: 'Guide' }},
		{path : 'guideComplaints', component : GuideComplaintComponent, canActivate: [AuthGuard], data: { role: 'Guide' }},
		{path : 'touristCheckout', component : TouristCheckoutComponent, canActivate: [AuthGuard], data: { role: 'Tourist' }},
		{path : 'touristComplaint', component : TouristComplaintComponent, canActivate: [AuthGuard], data: { role: 'Tourist' }},
		{path : 'touristGrade', component : TouristGradeComponent, canActivate: [AuthGuard], data: { role: 'Tourist' }},
		{path : 'adminComplaints', component : AdminComplaintComponent, canActivate: [AuthGuard], data: { role: 'Admin' }},
		{path : 'touristTours', component : TouristToursComponent, canActivate: [AuthGuard], data: { role: 'Tourist' }},
		{path : 'touristInterests', component : TouristInterestsComponent, canActivate: [AuthGuard], data: { role: 'Tourist' }},
		{path : 'adminMalicious', component : AdminMaliciousComponent, canActivate: [AuthGuard], data: { role: 'Admin' }},
		{path : 'touristViewComplaints', component : TouristViewComplaintComponent, canActivate: [AuthGuard], data: { role: 'Tourist' }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
