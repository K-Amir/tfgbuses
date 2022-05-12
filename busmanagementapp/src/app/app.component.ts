import { Component, OnInit } from '@angular/core';
import { AccountService } from './account/account.service';
import {
  faBusSimple,
  faWarning,
  faUser,
  faClock,
  faRightFromBracket,
} from '@fortawesome/free-solid-svg-icons';

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

  constructor(private accountService: AccountService) {}

  handleLogout() {
    this.accountService.signout();
  }

  ngOnInit(): void {
    this.accountService.loaduserfromjwt()?.subscribe();
    this.accountService.userSubject$.subscribe({
      next: (v) => {
        this.isLoggedIn = v;
      },
    });
  }
}
