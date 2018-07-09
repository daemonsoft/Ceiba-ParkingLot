import { BadInput } from '../common/bad-input';
import { NotFoundError } from '../common/not-found-error';
import { AppError } from '../common/app-error';
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { Vehicle } from '../components/vehicles/vehicles.component';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private url = 'http://localhost:8080/vehicle';
  constructor(private http: Http) { }

  getAll() {
    return this.http.get(this.url);
  }

  create(vehicle: Vehicle) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, JSON.stringify(vehicle), options);
  }
  
  vehicleExit(licensePlate: string) {
    return this.http.get(this.url + '/exit/' + licensePlate);
  }

  private handleError(error: Response) {
    if (error.status === 400)
      return Observable.throw(new BadInput(error.json()));

    if (error.status === 404)
      return Observable.throw(new NotFoundError());

    return Observable.throw(new AppError(error));
  }
}
