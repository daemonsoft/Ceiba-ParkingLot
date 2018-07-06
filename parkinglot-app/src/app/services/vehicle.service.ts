import { Injectable } from '@angular/core';
import { Http } from '../../../node_modules/@angular/http';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private url = 'http://localhost:8080/vehicle';
  constructor(private http: Http) { }

  getVehicles(){
    return this.http.get(this.url);
  }
}
