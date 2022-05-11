import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  public email!: string;
  public password!: string;

  constructor(private accountService: AccountService, private router: Router) {}

  handleFormSubmit() {
    this.accountService
      .signin(this.email, this.password)
      .subscribe((jwt: string) => {
        localStorage.setItem('jwt', jwt);
        this.router.navigateByUrl('/');
      });
  }

  ngOnInit(): void {}
}
