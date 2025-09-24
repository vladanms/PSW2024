
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as Leaflet from 'leaflet';
import { AddKeypointsService } from '../service/add-keypoints.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators'; 

@Component({
  selector: 'app-add-keypoints',
  templateUrl: './add-keypoints.component.html',
  styleUrl: './add-keypoints.component.css'
})
export class AddKeypointsComponent {

  private map!: Leaflet.Map;
  private keypointMarker: Leaflet.Marker | null = null; 
  addKeypointForm: FormGroup;
  errorMessage: string = '';
  image: File | null = null;

  constructor(private fb: FormBuilder,private addKeypointsService: AddKeypointsService ,private router: Router){
	  
      this.addKeypointForm = this.fb.group({		
	  name: ['', [Validators.required]],	
	  description: ['', [Validators.required]],
	  image: [null, [Validators.required]],
      longitude: [0, [Validators.required]],
      latitude: [0, [Validators.required]],
    });
  }


  ngOnInit(): void {
    this.initMap();
    Leaflet.Icon.Default.mergeOptions({
  	shadowUrl: null
	});
  }

  initMap(): void 
  {
	  
    this.map = Leaflet.map('map', {
    center: [45.2671, 19.8335], 
    zoom: 13 
  });

  Leaflet.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);
    
     this.map.on('click', (event: Leaflet.LeafletEvent) => {
     const mouseEvent = event as Leaflet.LeafletMouseEvent;

  	 const lat = parseFloat(mouseEvent.latlng.lat.toFixed(2));  
  	 const lng = parseFloat(mouseEvent.latlng.lng.toFixed(2));  


        this.addKeypointForm.patchValue({
          latitude: lat,
          longitude: lng
        });
      
       console.log("lat: ", lat);
       console.log("lng: ", lng);

      if (this.keypointMarker) {
        this.keypointMarker.remove();
      }

      this.keypointMarker = Leaflet.marker([lat, lng]).addTo(this.map);
    });  
  }
  
  

  onSubmit()
  {
	  
    	if (this.addKeypointForm.invalid) 
    	{
      		return;
    	}   	
    	const formData = new FormData();
   	 	const tourId = localStorage.getItem('tourId');
    	if(tourId)
   		{
    		formData.append('guideId', tourId);
    	}
    	else
    	{
			return
		}
		formData.append('name', this.addKeypointForm.get('name')?.value);
    	formData.append('description', this.addKeypointForm.get('description')?.value);
    	formData.append('image', this.image!);
   		formData.append('longitude', this.addKeypointForm.get('longitude')?.value.toString());
   		formData.append('latitude', this.addKeypointForm.get('latitude')?.value.toString());
   		
   		this.addKeypointsService.addKeypoint(formData).pipe(
      tap(response => {
          if (response && response.success) {
            console.log("Success!");
          } else if (response && response.error) {
            console.log("Error:", response.error);
          }
      })
    ).subscribe(); 
    }
    
      get formControls() 
    {
    return this.addKeypointForm.controls;
  	}
  
  
  
    onFileSelected(event: any): void 
    {
    	const file = event.target.files[0];
    	this.image = file;
    	this.addKeypointForm.patchValue({
      	image: file
    	});
  	}
}