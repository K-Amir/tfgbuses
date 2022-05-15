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
  public isLoading: boolean = false;
  public error!: string;

  constructor(private accountService: AccountService, private router: Router) {}

  handleFormSubmit() {
    this.error = '';
    if (!this.email || !this.password) {
      this.error = 'Please fill all the fields';
      return;
    }

    this.isLoading = true;
    this.accountService.signin(this.email, this.password).subscribe({
      next: (jwt: string) => {
        localStorage.setItem('jwt', jwt);
        this.router.navigateByUrl('/');
      },
      complete: () => {
        this.isLoading = false;
      },
      error: (e) => {
        console.log(e);
        this.isLoading = false;
        this.error = 'Invalid credentials';
      },
    });
  }

  ngOnInit(): void {}
}
