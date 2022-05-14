import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class IncidencesService {
  baseUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) {}

  addIncidence(incidence: any) {
    return this.http.post(
      `${this.baseUrl}empresa/v0/incidences`,
      {
        ...incidence,
      },
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }
    );
  }

  getAllIncidences() {
    return this.http.get(`${this.baseUrl}empresa/v0/incidences`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    });
  }
}
