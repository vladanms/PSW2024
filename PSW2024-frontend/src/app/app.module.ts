import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
import { MatLabel } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ToastrModule } from 'ngx-toastr';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RegisteredHomepageComponent } from './registered-homepage/registered-homepage.component';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { GuideHomepageComponent } from './guide-homepage/guide-homepage.component';
import { AddKeypointsComponent } from './add-keypoints/add-keypoints.component';
import { CreateTourComponent } from './create-tour/create-tour.component';
import { GuideDraftsComponent } from './guide-drafts/guide-drafts.component';
import { TouristToursComponent } from './tourist-tours/tourist-tours.component';
import { GuideToursComponent } from './guide-tours/guide-tours.component';
import { TouristCheckoutComponent } from './tourist-checkout/tourist-checkout.component';
import { TouristGradeComponent } from './tourist-grade/tourist-grade.component';
import { TouristComplaintComponent } from './tourist-complaint/tourist-complaint.component';
import { GuideComplaintComponent } from './guide-complaint/guide-complaint.component';
import { AdminComplaintComponent } from './admin-complaint/admin-complaint.component';
import { MainHeaderComponent } from './main-header/main-header.component';
import { TouristInterestsComponent } from './tourist-interests/tourist-interests.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    RegisteredHomepageComponent,
    AdminHomepageComponent,
    GuideHomepageComponent,
    AddKeypointsComponent,
    CreateTourComponent,
    GuideDraftsComponent,
    TouristToursComponent,
    GuideToursComponent,
    TouristCheckoutComponent,
    TouristGradeComponent,
    TouristComplaintComponent,
    GuideComplaintComponent,
    AdminComplaintComponent,
    MainHeaderComponent,
    TouristInterestsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    RouterModule,
    AppRoutingModule,
    FormsModule,
    MatLabel,
    MatInputModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule,
    MatButtonModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
