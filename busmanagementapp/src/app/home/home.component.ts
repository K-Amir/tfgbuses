import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account/account.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  isLoggedIn!: boolean | null;
  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    this.accountService.userSubject$.subscribe({
      next: (v) => {
        this.isLoggedIn = v;
      },
    });
  }
}
