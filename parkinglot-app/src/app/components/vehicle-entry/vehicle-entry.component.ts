import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../../services/vehicle.service';
import { Vehicle, VehicleType } from '../vehicles/vehicles.component';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { InvoiceService } from '../../services/invoice.service';

@Component({
  selector: 'pl-vehicle-entry',
  templateUrl: './vehicle-entry.component.html',
  styleUrls: ['./vehicle-entry.component.scss']
})
export class VehicleEntryComponent implements OnInit {

  licensePlate: string = "";
  inputText: string;
  constructor(private vehicleService: VehicleService, private invoiceService: InvoiceService, private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
  validLicensePlate(): boolean {
    return (this.licensePlate.trim().length < 1)
  }

  vehicleExit() {
    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida");
      return;
    }

    this.vehicleService.vehicleExit(this.licensePlate)
      .subscribe(invoiceResponse => console.log(invoiceResponse.json()));
  }

  saveCar($event) {

    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida");
      return;
    }
    let vehicle = {
      licensePlate: this.licensePlate,
      type: VehicleType.CAR
    };
    console.log(this.licensePlate);
    this.vehicleService.create(vehicle)
      .subscribe(
        newVehicle => {
          console.log(newVehicle.json());
          this.openSnackbar("Vehiculo ingresado");
        });

    console.log("save car ", this.licensePlate);
  }

  saveBike($event) {
    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida");
      return;
    }
    let vehicle = {
      licensePlate: this.licensePlate,
      type: VehicleType.BIKE
    };
    console.log(this.licensePlate);
    this.vehicleService.create(vehicle)
      .subscribe(
        newVehicle => {
          console.log(newVehicle.json());
          this.router.navigateByUrl("");
          this.openSnackbar("Vehiculo ingresado");
        });
    console.log("save saveBike ", this.licensePlate);
  }

  saveBigBike($event) {
    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida");
      return;
    }
    let vehicle = {
      licensePlate: this.licensePlate,
      type: VehicleType.BIKE,
      engineCapacity: 500
    };
    console.log(this.licensePlate);
    this.vehicleService.create(vehicle)
      .subscribe(
        newVehicle => {
          console.log(newVehicle.json());
          this.router.navigateByUrl("");
        });
    console.log("save saveBigBike ", this.licensePlate);
  }

  onKeyUp() {
    console.log(this.licensePlate);
    this.inputText = this.licensePlate;
  }

  openSnackbar(message: string) {
    this.snackBar.open(message, "Cerrar", {
      duration: 2000,
    });
  }
}