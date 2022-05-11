import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, ReplaySubject, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BusService {
  baseUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) {}

  addBus(bus: any) {
    return this.http.post(
      `${this.baseUrl}empresa/v0/buses`,
      {
        ...bus,
      },
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }
    );
  }

  getAllBuses() {
    return this.http.get(`${this.baseUrl}empresa/v0/buses`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    });
  }
  deleteBusById(id: string) {
    return this.http.delete(`${this.baseUrl}empresa/v0/buses/${id}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    });
  }
}
