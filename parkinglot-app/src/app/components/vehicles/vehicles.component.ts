import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../../services/vehicle.service';

export interface Vehicle {
  id?: number;
  licensePlate: string;
  type: VehicleType;
  engineCapacity?: number;
}

export enum VehicleType {
  CAR = 0, BIKE = 1
}

@Component({
  selector: 'pl-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.scss']
})
export class VehiclesComponent implements OnInit {
  vehicles: Vehicle[];
  constructor(private service: VehicleService) { }

  ngOnInit() {
    this.service.getAll()
      .subscribe(vehicles => this.vehicles = vehicles.json());
  }
}
