import { Component } from '@angular/core';
import { TouristToursService } from '../service/tourist-tours.service';
import { TourDTO } from '../dto/TourDTO';
import { GradeDTO } from '../dto/GradeDTO';
import { ComplaintDTO } from '../dto/ComplaintDTO';
import { KeyPointDTO } from '../dto/KeyPointDTO';
import * as Leaflet from 'leaflet';
import { Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-tourist-tours',
  templateUrl: './tourist-tours.component.html',
  styleUrl: './tourist-tours.component.css'
})
export class TouristToursComponent {
	
  tours: TourDTO[] = [];
  selectedKeyPoint: KeyPointDTO | null = null;
  constructor(private touristToursService: TouristToursService, private router: Router, private cdRef : ChangeDetectorRef) {}
  initializedMapIds = new Set<number>();

  ngOnInit(): void 
  {
   const tourist = localStorage.getItem('loggedUser');
      if (!tourist) {
      alert('Tourist not found in localStorage');
      return;
   }

   this.touristToursService.getAvailable(tourist).subscribe({
     next: (tours) => {
     	this.tours = tours;
     	this.cdRef.detectChanges();
     	setTimeout(() => {
          	this.tours.forEach(tour => this.initMap(tour));
        }, 50);
      },
      error: err => console.error('Error loading tours', err)
    });
  }
  
    initMap(tour: TourDTO): void 
    {
  	const mapId = `map-${tour.id}`;
  	
  	if (this.initializedMapIds.has(tour.id))
	{ 
		return;
	}
	if (!tour.keyPoints || tour.keyPoints.length === 0)
	{
    	return;
  	}
  	  	
  	
  	const startingPoint = tour.keyPoints[0];
  	const center: Leaflet.LatLngExpression = [startingPoint.latitude, startingPoint.longitude];
  	
  	 const map = Leaflet.map(mapId).setView(center, 13);
  	 
  	  Leaflet.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    
     const keyPointCoordinates: Leaflet.LatLngExpression[] = [];
    
     tour.keyPoints.forEach(kp => {
  
         const coordinates: Leaflet.LatLngExpression = [kp.latitude, kp.longitude];
   		 keyPointCoordinates.push(coordinates);
   		 const marker = Leaflet.marker(coordinates).addTo(map);
    	 marker.bindPopup(kp.name);

  		 marker.on('click', () => 
  		 {
         this.selectedKeyPoint = kp;
  		 });
  	}); 
  	const polyline = Leaflet.polyline(keyPointCoordinates, { color: 'red' }).addTo(map);
  	map.fitBounds(polyline.getBounds());    
  }


	grade(tour : TourDTO) : void
	{
		localStorage.setItem('tourId', tour.id.toString());
		this.router.navigate(['/touristGrade'])		
	}
	
	complaint(tour : TourDTO) : void
	{
		localStorage.setItem('guide', tour.guide);
		localStorage.setItem('tourId', tour.id.toString());
		this.router.navigate(['/touristComplaint'])	
	}
	
	back()
  	{
		  this.router.navigate(['/touristHomepage'])
	}

}
