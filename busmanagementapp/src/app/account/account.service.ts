import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  baseUrl: string = environment.apiUrl;
  userSubject$ = new BehaviorSubject<null | boolean>(null);

  constructor(private http: HttpClient, private router: Router) {}

  signin(email: string, password: string) {
    return this.http
      .get<any>(`${this.baseUrl}empresa/v0/auth/token`, {
        headers: {
          user: email,
          password: password,
          Accept: 'text/plain',
        },
        responseType: 'text' as 'json',
      })
      .pipe(
        tap((jwt: any) => {
          this.userSubject$.next(true);
        })
      );
  }

  getAllAdmins() {
    return this.http.get(`${this.baseUrl}empresa/v0/auth/admin`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    });
  }

  deleteByEmail(email: string) {
    return this.http.delete(`${this.baseUrl}empresa/v0/auth/${email}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`,
      },
    });
  }

  addNewAdmin(appUserInput: any) {
    return this.http.post(
      `${this.baseUrl}empresa/v0/auth`,
      {
        ...appUserInput,
      },
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }
    );
  }

  loaduserfromjwt() {
    let jwt: string | null = localStorage.getItem('jwt');
    if (jwt) {
      return this.http.get(`${this.baseUrl}empresa/v0/auth/token/${jwt}`).pipe(
        map((val) => {
          this.userSubject$.next(true);
        })
      );
    }
    this.userSubject$.next(false);
    return null;
  }

  signout() {
    this.userSubject$.next(null);
    localStorage.removeItem('jwt');

    this.router.navigate(['/account/login']);
  }
}
