import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TouristCheckoutService } from '../service/tourist-checkout.service';
import { TourDTO } from '../dto/TourDTO';

@Component({
  selector: 'app-tourist-checkout',
  templateUrl: './tourist-checkout.component.html',
  styleUrl: './tourist-checkout.component.css'
})
export class TouristCheckoutComponent {
	
 tours: {id: number,  name: string, price: number}[] = [];
  totalPrice: number = 0;
  finalPrice: number = 0; 
  rewardPoints: number = 0;
  useRewardPoints: boolean = false;
  selectedTourIds: number[] = [];
  loggedUser: string = '';
  
   constructor(private router: Router,private touristCheckoutService: TouristCheckoutService) { }
   
   ngOnInit(): void {
    const tourist = localStorage.getItem('loggedUser');
    if (!tourist) {
      alert('Tourist not found in local storage');
      return;
    }
    this.loggedUser = tourist;
    this.refreshCart();
    this.getRewardPoints();
  }
  
  refreshCart(): void 
  {
  const tourIds = JSON.parse(localStorage.getItem('tourIds') || '[]');
  const cart = JSON.parse(localStorage.getItem('cart') || '[]');
  const prices = JSON.parse(localStorage.getItem('tourPrices') || '[]');

  this.tours = cart.map((name: string, index: number) => {
    return {
      id: tourIds[index],
      name: name,
      price: prices[index]
    };
  });

  this.totalPrice = prices.reduce((acc: number, price: number) => acc + price, 0);
  this.selectedTourIds = tourIds;
  this.updatePrice(); 
 }
  
  getRewardPoints(): void 
  {
  this.touristCheckoutService.getRewardPoints(this.loggedUser).subscribe({
    next: (points) => {
      this.rewardPoints = points;
    }
  });
  }

 toggleRewardPoints(event: any): void 
 {
  	this.useRewardPoints = event.target.checked;  	
  	this.updatePrice();
  	this.refreshCart();
 }
 
 updatePrice(): void 
 {
    if (this.useRewardPoints) 
    {
      this.finalPrice = Math.max(this.totalPrice - this.rewardPoints, 0);  
    } 
    else 
    {
      this.finalPrice = this.totalPrice;
    }
  }
 
 removeFromCart(tour: {id: number,  name: string, price: number}): void
 {
  	const tourIds = JSON.parse(localStorage.getItem('tourIds') || '[]');
  	const cart = JSON.parse(localStorage.getItem('cart') || '[]');
  	const prices = JSON.parse(localStorage.getItem('tourPrices') || '[]');

  	const index = tourIds.indexOf(tour.id);
  
    tourIds.splice(index, 1);
    cart.splice(index, 1);
    prices.splice(index, 1);
    localStorage.setItem('tourIds', JSON.stringify(tourIds));
    localStorage.setItem('cart', JSON.stringify(cart));
    localStorage.setItem('tourPrices', JSON.stringify(prices));

  this.refreshCart();
}

 checkout(): void 
 {
    const tourIds = this.selectedTourIds.map(id => id.toString());
    const usePoints = this.useRewardPoints;

    this.touristCheckoutService.reserveTours(this.loggedUser, usePoints, tourIds).subscribe({
      next: () => {
            localStorage.removeItem('tourIds');
    		localStorage.removeItem('cart');
    		localStorage.removeItem('tourPrices');
      }
    });
	this.router.navigate(['/registeredHomepage'])
  }
    back()
  	{
		  this.router.navigate(['/registeredHomepage'])
	}

}