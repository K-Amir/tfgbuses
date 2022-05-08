import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'buses',
    loadChildren: () =>
      import('./buses/buses.module').then((m) => m.BusesModule),
  },
  {
    path: 'account',
    loadChildren: () =>
      import('./account/account.module').then((m) => m.AccountModule),
  },
  {
    path: 'incidences',
    loadChildren: () =>
      import('./incidences/incidences.module').then((m) => m.IncidencesModule),
  },
  {
    path: 'history',
    loadChildren: () =>
      import('./bookinghistory/bookinghistory.module').then(
        (m) => m.BookinghistoryModule
      ),
  },
  {
    path: 'accounts',
    loadChildren: () =>
      import('./account/account.module').then((m) => m.AccountModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
