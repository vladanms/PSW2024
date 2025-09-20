
import { Component, OnInit } from '@angular/core';
import * as Leaflet from 'leaflet';

@Component({
  selector: 'app-add-keypoints',
  templateUrl: './add-keypoints.component.html',
  styleUrl: './add-keypoints.component.css'
})
export class AddKeypointsComponent {

 private map!: Leaflet.Map;

  ngOnInit(): void {
    this.initMap();
  }

  initMap(): void {
    this.map = Leaflet.map('map', {
    center: [45.2671, 19.8335], 
    zoom: 13 
  });

    Leaflet.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);


}
}