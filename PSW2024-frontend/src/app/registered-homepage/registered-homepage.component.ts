import { Component } from '@angular/core';
import { RegisteredHomepageService } from '../service/registered-homepage.service';
import { TourDTO } from '../dto/TourDTO';
import { GradeDTO } from '../dto/GradeDTO';
import { ComplaintDTO } from '../dto/ComplaintDTO';
import { KeyPointDTO } from '../dto/KeyPointDTO';
import * as Leaflet from 'leaflet';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { ChangeDetectorRef } from '@angular/core';



@Component({
  selector: 'app-registered-homepage',
  templateUrl: './registered-homepage.component.html',
  styleUrl: './registered-homepage.component.css'
})
export class RegisteredHomepageComponent {
	
  tours: TourDTO[] = [];
  selectedKeyPoint: KeyPointDTO | null = null;
  constructor(private registeredHomepageService: RegisteredHomepageService, private router: Router, private loginService : LoginService, private cdRef : ChangeDetectorRef) {}
  initializedMapIds = new Set<number>();
  showAwarded : boolean = false;

  ngOnInit(): void 
  {
     this.loadTours();
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
  
   selectKeyPoint(keypoint: KeyPointDTO)
   {
    this.selectedKeyPoint = keypoint;
   }
  
  loadTours(): void
   {
  const tourist = localStorage.getItem('loggedUser');
  if (!tourist) {
    alert('Tourist not found in localStorage');
    return;
  }

  const awarded = this.showAwarded
    ? this.registeredHomepageService.getAwarded(tourist)
    : this.registeredHomepageService.getAvailable(tourist);

  awarded.subscribe({
    next: (tours) => {
      this.tours = tours;
      this.selectedKeyPoint = null;
      this.cdRef.detectChanges();
      setTimeout(() => this.tours.forEach(t => this.initMap(t)), 50);
    },
    error: err => console.error('Error loading tours', err)
  });
}
  
  viewAwarded(): void 
  {
  this.showAwarded = !this.showAwarded;
  this.loadTours();
  }
  
  AddToCart(tour: TourDTO): void 
  {
  	let tourIds: number[] = JSON.parse(localStorage.getItem('tourIds') || '[]');
  	let tourPrices: number [] = JSON.parse(localStorage.getItem('tourPrices') || '[]');
  	let totalPrice: number = JSON.parse(localStorage.getItem('totalPrice') || '0');
  	let cart : string[] = JSON.parse(localStorage.getItem('cart') || '[]');
  	
  	if (!tourIds.includes(tour.id)) 
  	{
   	 	tourIds.push(tour.id);
   	 	cart.push(tour.name);
   	 	tourPrices.push(tour.price);
   	 	totalPrice = totalPrice + tour.price;
    	localStorage.setItem('tourIds', JSON.stringify(tourIds));
    	localStorage.setItem('totalPrice', JSON.stringify(totalPrice));
    	localStorage.setItem('cart', JSON.stringify(cart));
    	localStorage.setItem('tourPrices', JSON.stringify(tourPrices));
    }
  this.refresh();
  }
  
  RemoveFromCart(tour: TourDTO): void 
  {
    let tourIds = JSON.parse(localStorage.getItem('tourIds') || '[]');
    let tourPrices = JSON.parse(localStorage.getItem('tourPrices') || '[]');
    let totalPrice = JSON.parse(localStorage.getItem('totalPrice') || '0');
    let cart : string[] = JSON.parse(localStorage.getItem('cart') || '[]');
    
    const index = tourIds.indexOf(tour.id);
    
    tourIds.splice(index, 1); 
    cart.splice(index, 1);
    tourPrices.splice(index, 1);
    totalPrice = totalPrice- tour.price; 
    
    localStorage.setItem('tourIds', JSON.stringify(tourIds)); 
    localStorage.setItem('totalPrice', JSON.stringify(totalPrice));
    localStorage.setItem('cart', JSON.stringify(cart));
    localStorage.setItem('tourPrices', JSON.stringify(tourPrices));
    this.refresh();  
  }
  
  isInCart(tour: TourDTO): boolean 
  {
  	const tourIds: number[] = JSON.parse(localStorage.getItem('tourIds') || '[]');
  	return tourIds.includes(tour.id);
  }

  refresh(): void 
  {
      this.loadTours();
  }  
  
  checkout() : void
  {
	  this.router.navigate(['/touristCheckout'])
  }
  
  myTours() : void
  {
	  this.router.navigate(['/touristTours'])
  }
  
   myComplaints() : void
  {
	  this.router.navigate(['/touristViewComplaints'])
  }
  
  logout(): void 
	{
  	this.loginService.logout().subscribe({
    	next: () => {
      localStorage.removeItem('loggedUser');
      localStorage.removeItem('tourId');
      this.router.navigate(['/login']);
    		}
  		});
	}

	editInterests() : void
	{
		this.router.navigate(['/touristInterests'])
	}

}
