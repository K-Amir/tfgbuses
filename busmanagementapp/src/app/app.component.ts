import { Component, OnInit } from '@angular/core';
import { AccountService } from './account/account.service';
import {
  faBusSimple,
  faWarning,
  faUser,
  faClock,
  faRightFromBracket,
} from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  public isMenuCollapsed = false;
  public isLoggedIn!: boolean | null;

  faBusSimple = faBusSimple;
  faWarning = faWarning;
  faUser = faUser;
  faClock = faClock;
  faRightFromBracket = faRightFromBracket;

  constructor(private accountService: AccountService, private router: Router) {}

  handleLogout() {
    this.accountService.signout();
  }

  ngOnInit(): void {
    this.accountService.loaduserfromjwt()?.subscribe({
      error: () => {
        this.router.navigateByUrl('account/login');
      },
    });
    this.accountService.userSubject$.subscribe({
      next: (v) => {
        this.isLoggedIn = v;
      },
    });
  }
}
