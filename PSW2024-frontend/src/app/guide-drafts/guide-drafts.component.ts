import { Component } from '@angular/core';
import { GuideDraftsService } from '../service/guide-drafts.service';
import { TourDTO } from '../dto/TourDTO';
import { GradeDTO } from '../dto/GradeDTO';
import { ComplaintDTO } from '../dto/complaintDTO';
import { KeyPointDTO } from '../dto/KeyPointDTO';
import * as Leaflet from 'leaflet';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guide-drafts',
  templateUrl: './guide-drafts.component.html',
  styleUrl: './guide-drafts.component.css'
})
export class GuideDraftsComponent {

  tours: TourDTO[] = [];
  selectedKeyPoint: KeyPointDTO | null = null;
  constructor(private guideDraftsService: GuideDraftsService, private router: Router) {}

  ngOnInit(): void 
  {
    const guide = localStorage.getItem('guide');
    if (!guide) {
      alert('Guide not found in localStorage');
      return;
   }

   this.guideDraftsService.getDrafts(guide).subscribe({
      next: (tours) => {
        this.tours = tours;
        setTimeout(() => {
          this.tours.forEach(tour => this.initMap(tour));
        }, 0);
      },
      error: err => console.error('Error loading drafts', err)
    });
  }

  initMap(tour: TourDTO): void 
  {
  	const mapId = `map-${tour.id}`;
	      
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
  
   selectKeyPoint(keypoint: KeyPointDTO) {
    this.selectedKeyPoint = keypoint;
  }

  publishTour(tour : TourDTO): void 
  {
    if (tour.keyPoints.length < 2) 
    {
		return;
	}

    this.guideDraftsService.publish(tour.id).subscribe(() => {
        this.refresh();
    });
  }

  deleteTour(tour : TourDTO): void 
  {
  	this.guideDraftsService.delete(tour.id).subscribe(() => {
    this.refresh();
  	});
  }

  refresh(): void 
  {
    const guide = localStorage.getItem('guide');
    if (!guide) return;
    this.guideDraftsService.getDrafts(guide).subscribe(tours => {
      this.tours = tours;
      this.selectedKeyPoint = null;
      setTimeout(() => this.tours.forEach(t => this.initMap(t)), 0);
    });
  }

  addKeypoint(tour : TourDTO)
  {
	  localStorage.setItem('tourId', tour.id.toString());
	  this.router.navigate(['/addKeypoint']);
  }
}
