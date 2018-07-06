import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'pl-vehicle-entry',
  templateUrl: './vehicle-entry.component.html',
  styleUrls: ['./vehicle-entry.component.scss']
})
export class VehicleEntryComponent implements OnInit {

  licensePlate = "";

  text;

  constructor() { }

  ngOnInit() {
  }

  saveCar($event) {
    console.log("save car ", this.licensePlate);
  }

  saveBike($event) {
    console.log("save saveBike ", this.licensePlate);
  }

  saveBigBike($event) {
    console.log("save saveBigBike ", this.licensePlate);
  }

  onKeyUp() {
    console.log(this.licensePlate);
    this.text = this.licensePlate;
  }
}