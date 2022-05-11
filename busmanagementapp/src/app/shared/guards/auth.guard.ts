import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { map, Observable, skipWhile, take, tap } from 'rxjs';
import { AccountService } from 'src/app/account/account.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private accountService: AccountService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {
    return this.accountService.userSubject$.pipe(
      skipWhile((value) => value === null),
      take(1),
      map((loggedin) => {
        if (loggedin) {
          return true;
        }
        this.router.navigateByUrl('account/login');
        return false;
      })
    );
  }
}
