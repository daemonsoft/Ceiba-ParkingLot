import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../../services/vehicle.service';

@Component({
  selector: 'pl-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.scss']
})
export class VehiclesComponent implements OnInit {
  vehicles: any[];
  constructor(private service: VehicleService) { }

  ngOnInit() {
    this.service.getVehicles()
      .subscribe(response => {
        this.vehicles = response.json();
      });
  }
}
