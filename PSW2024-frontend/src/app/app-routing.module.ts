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

const routes: Routes = [
		{path: 'login', component : LoginComponent},
		{path: 'register', component : RegisterComponent},
		{path: 'registeredHomepage', component : RegisteredHomepageComponent},
		{path: 'adminHomepage', component : AdminHomepageComponent},
		{path: 'guideHomepage', component : GuideHomepageComponent},
		{path: 'addKeypoints', component : AddKeypointsComponent},
		{path :'createTour', component : CreateTourComponent},
		{path :'guideDrafts', component : GuideDraftsComponent},
		{path : 'guideComplaint', component : GuideComplaintComponent},
		{path : 'touristCheckout', component : TouristCheckoutComponent},
		{path : 'touristComplaint', component : TouristComplaintComponent},
		{path : 'touristGrade', component : TouristGradeComponent},
		{path : 'adminComplaint', component : AdminComplaintComponent},
		{path : 'touristTours', component : TouristToursComponent},
		{path : 'touristInterests', component : TouristInterestsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
