import { Component } from '@angular/core';
import { GuideDraftsService } from '../service/guide-drafts.service';
import { TourDTO } from '../dto/TourDTO';
import { GradeDTO } from '../dto/GradeDTO';
import { ComplaintDTO } from '../dto/ComplaintDTO';
import { KeyPointDTO } from '../dto/KeyPointDTO';
import * as Leaflet from 'leaflet';
import { Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-guide-drafts',
  templateUrl: './guide-drafts.component.html',
  styleUrl: './guide-drafts.component.css'
})
export class GuideDraftsComponent {

  tours: TourDTO[] = [];
  filteredTours: TourDTO[] = [];
  selectedKeyPoint: KeyPointDTO | null = null;
  constructor(private guideDraftsService: GuideDraftsService, private router: Router,  private cdRef: ChangeDetectorRef) {}
  initializedMapIds = new Set<number>();
  filterOptions = ["Published", "Draft", "All"]
  filter: string = 'All';

  ngOnInit(): void 
  {
    const guide = localStorage.getItem('loggedUser');
    if (!guide) {
      alert('Guide not found in localStorage');
      return;
   }

   this.guideDraftsService.getDrafts(guide).subscribe({
     next: (tours) => {
     	this.tours = tours;
     	this.filterTours()
     	this.cdRef.detectChanges();
     	setTimeout(() => {
          	this.filteredTours.forEach(tour => this.initMap(tour));
        }, 50);
      },
      error: err => console.error('Error loading drafts', err)
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
  
   selectKeyPoint(keypoint: KeyPointDTO) {
    this.selectedKeyPoint = keypoint;
  }
  
  filterTours(): void {
      if( this.filter === 'Published')
      {
        this.filteredTours = this.tours.filter(tour => tour.published);
        return;
      }
      if( this.filter === 'Draft')
      {
        this.filteredTours = this.tours.filter(tour => !tour.published);
        return;
      }
      if( this.filter === 'All')
      {
        this.filteredTours = this.tours;
        return;
      }
  }

  publishTour(tour : TourDTO): void 
  {
    if (tour.keyPoints.length < 2) 
    {
		return;
	}

    this.guideDraftsService.publish(tour.id.toString()).subscribe(() => {
        this.refresh();
    });
  }

  deleteTour(tour : TourDTO): void 
  {
	const guide = localStorage.getItem('loggedUser');
    if (!guide) {
      alert('Guide not found in localStorage');
      return;
   }
  	this.guideDraftsService.delete(guide, tour.id.toString()).subscribe(() => {
    this.refresh();
  	});
  }

  refresh(): void 
  {
    const guide = localStorage.getItem('loggedUser');
    if (!guide) return;
    this.guideDraftsService.getDrafts(guide).subscribe(tours => {
      this.tours = tours;
      this.filterTours();
      this.selectedKeyPoint = null;
      this.initializedMapIds.clear();
      this.cdRef.detectChanges();
      setTimeout(() => this.filteredTours.forEach(t => this.initMap(t)), 50);
    });
  }

  addKeypoint(tour : TourDTO)
  {
	  localStorage.setItem('tourId', tour.id.toString());
	  this.router.navigate(['/addKeypoints']);
  }
  
  back()
  {
	  this.router.navigate(['/guideHomepage'])
  }
}
