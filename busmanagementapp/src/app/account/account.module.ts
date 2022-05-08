import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { AccountRoutingModule } from './account-routing.module';
import { AccountsComponent } from './accounts/accounts.component';

@NgModule({
  declarations: [LoginComponent, AccountsComponent],
  imports: [CommonModule, AccountRoutingModule],
})
export class AccountModule {}
