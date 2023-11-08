import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map} from "rxjs";
import {environment} from '../environments/environment';
import {Position} from "../model/vehicle";
import {VehiclePreview, VehiclePreviewI} from "../model/vehicle-preview";

@Injectable()
export class VehicleService {

  host: string = environment.apiUrl + 'vehicle/'

  constructor(private http: HttpClient) {
  }

  getNearest(lat1: number,lng1: number, lat2: number, lng2: number) {
    const url = `${this.host}get-nearest/${lat1}/${lng1}/${lat2}/${lng2}`;
    return this.http.get<VehiclePreviewI[]>(url)
  }

  bookTaxi(vehicle: VehiclePreview) {
    return this.http.post(`${this.host}book-vehicle`,vehicle)
  }
}
