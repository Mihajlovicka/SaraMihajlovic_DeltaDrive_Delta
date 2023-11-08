import {Component, OnInit} from '@angular/core';
import {VehicleService} from "../service/vehicle.service";
import {VehiclePreview} from "../model/vehicle-preview";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-nearest-vehicles',
  templateUrl: './nearest-vehicles.component.html',
  styleUrls: ['./nearest-vehicles.component.css']
})
export class NearestVehiclesComponent{
  nearest:VehiclePreview[] = []

  constructor(private service: VehicleService, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      const lat1 = params['lat1'];
      const lng1 = params['lng1'];
      const lat2 = params['lat2'];
      const lng2 = params['lng2'];
      this.retrieveNearestVehicles(lat1, lng1, lat2, lng2);
    });
  }

  private retrieveNearestVehicles(lat1: number, lng1: number, lat2: number, lng2: number) {
    this.service.getNearest(lat1, lng1, lat2, lng2).subscribe({
      next: (r) => {
        this.nearest = r;
        this.sortNearestByTotalPrice();
      }
    });
  }

  private sortNearestByTotalPrice() {
    this.nearest.sort((a, b) => a.totalPrice - b.totalPrice);
  }


  bookTaxi(vehicle: VehiclePreview) {
    this.service.bookTaxi(vehicle).subscribe({
      next: (resp) => {
        if(resp)
         alert("Taxi is on it's way!")
        else
          alert("Driver has rejected this request!")
      }
    });
  }
}
