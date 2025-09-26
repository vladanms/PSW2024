import { Component } from '@angular/core';
import { RegisteredHomepageService } from '../service/registered-homepage.service';
import { TourDTO } from '../dto/TourDTO';
import { GradeDTO } from '../dto/GradeDTO';
import { ComplaintDTO } from '../dto/ComplaintDTO';
import { KeyPointDTO } from '../dto/KeyPointDTO';
import * as Leaflet from 'leaflet';
import { Router } from '@angular/router';


@Component({
  selector: 'app-registered-homepage',
  templateUrl: './registered-homepage.component.html',
  styleUrl: './registered-homepage.component.css'
})
export class RegisteredHomepageComponent {
	
  tours: TourDTO[] = [];
  selectedKeyPoint: KeyPointDTO | null = null;
  constructor(private registeredHomepageService: RegisteredHomepageService, private router: Router) {}
  initializedMapIds = new Set<number>();

  ngOnInit(): void 
  {
   const tourist = localStorage.getItem('loggedUser');
      if (!tourist) {
      alert('Tourist not found in localStorage');
      return;
   }

   this.registeredHomepageService.getAvailable(tourist).subscribe({
     next: (tours) => {
     	this.tours = tours;
     	setTimeout(() => {
          	this.tours.forEach(tour => this.initMap(tour));
        }, 0);
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
  
   selectKeyPoint(keypoint: KeyPointDTO) {
    this.selectedKeyPoint = keypoint;
  }
  
  AddToCart(tour: TourDTO): void 
  {
  	let tourIds: number[] = JSON.parse(localStorage.getItem('tourIds') || '[]');
  	let totalPrice: number = JSON.parse(localStorage.getItem('totalPrice') || '0');
  	let cart : string[] = JSON.parse(localStorage.getItem('cart') || '[]');
  	
  	if (!tourIds.includes(tour.id)) 
  	{
   	 	tourIds.push(tour.id);
   	 	cart.push(tour.name);
   	 	totalPrice = totalPrice + tour.price;
    	localStorage.setItem('tourIds', JSON.stringify(tourIds));
    	localStorage.setItem('totalPrice', JSON.stringify(totalPrice));
    	localStorage.setItem('cart', JSON.stringify(cart));
    }
  this.refresh();
  }
  
  RemoveFromCart(tour: TourDTO): void 
  {
    let tourIds = JSON.parse(localStorage.getItem('tourIds') || '[]');
    let totalPrice: number = JSON.parse(localStorage.getItem('totalPrice') || '0');
    let cart : string[] = JSON.parse(localStorage.getItem('cart') || '[]');
    
    const index = tourIds.indexOf(tour.id);
    
    tourIds.splice(index, 1); 
    cart.splice(index, 1);
    totalPrice = totalPrice- tour.price; 
    
    localStorage.setItem('tourIds', JSON.stringify(tourIds)); 
    localStorage.setItem('totalPrice', JSON.stringify(totalPrice));
    localStorage.setItem('cart', JSON.stringify(cart));
    this.refresh();  
  }
  
  isInCart(tour: TourDTO): boolean 
  {
  	const cart: number[] = JSON.parse(localStorage.getItem('cart') || '[]');
  	return cart.includes(tour.id);
  }

  refresh(): void 
  {
    const tourist = localStorage.getItem('loggedUser');
    if (!tourist) return;
    this.registeredHomepageService.getAvailable(tourist).subscribe(tours => {
      this.tours = tours;
      this.selectedKeyPoint = null;
      setTimeout(() => this.tours.forEach(t => this.initMap(t)), 0);
    });
  }



}
