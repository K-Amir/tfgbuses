import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookinghistoryComponent } from './bookinghistory/bookinghistory.component';

const routes: Routes = [
  {
    path: '',
    component: BookinghistoryComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BookinghistoryRoutingModule {}
