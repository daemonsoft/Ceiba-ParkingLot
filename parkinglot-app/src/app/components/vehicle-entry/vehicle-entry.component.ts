import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../../services/vehicle.service';
import { VehicleType } from '../vehicles/vehicles.component';
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
  constructor(private vehicleService: VehicleService, private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
  validLicensePlate(): boolean {
    return (this.licensePlate.trim().length < 1)
  }

  vehicleExit(input: HTMLInputElement) {
    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida", 2000);
      return;
    }

    this.vehicleService.vehicleExit(this.licensePlate)
      .subscribe(invoiceResponse => {

        this.openSnackbar("Total a pagar: " + invoiceResponse.json().amount, 5000);

        console.log(invoiceResponse.json())
      },
        (error) => {
          if (error.status === 400) {
            this.openSnackbar(error._body, 2000);
          }
        });
  }

  saveCar(input: HTMLInputElement) {

    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida", 2000);
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
          input.value = '';
          this.openSnackbar("Vehiculo " + this.inputText + " ingresado", 2000);
        },
        (error) => {
          if (error.status === 400) {
            this.openSnackbar(error._body, 2000);
          }
        });

    console.log("save car ", this.licensePlate);
  }

  saveBike(input: HTMLInputElement) {
    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida", 2000);
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
          input.value = '';
          this.router.navigateByUrl("");
          this.openSnackbar("Vehiculo ingresado", 2000);
        },
        (error) => {
          if (error.status === 400) {
            this.openSnackbar(error._body, 2000);
          }
        });
    console.log("save saveBike ", this.licensePlate);
  }

  saveBigBike(input: HTMLInputElement) {
    if (this.validLicensePlate()) {
      this.openSnackbar("Placa no válida", 2000);
      return;
    }
    let vehicle = {
      licensePlate: this.licensePlate,
      type: VehicleType.BIKE,
      engineCapacity: 650
    };
    console.log(this.licensePlate);
    this.vehicleService.create(vehicle)
      .subscribe(
        newVehicle => {
          input.value = '';
          console.log(newVehicle.json());
          this.router.navigateByUrl("");
        },
        (error) => {
          if (error.status === 400) {
            this.openSnackbar(error._body, 2000);
          }
        });
    console.log("save saveBigBike ", this.licensePlate);
  }

  onKeyUp() {
    this.licensePlate = this.licensePlate.toUpperCase();
    console.log(this.licensePlate);
    this.inputText = this.licensePlate;
  }

  openSnackbar(message: string, duration: number) {
    this.snackBar.open(message, "Cerrar", {
      duration: duration,
    });
  }
}