import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookinghistoryRoutingModule } from './bookinghistory-routing.module';
import { BookinghistoryComponent } from './bookinghistory/bookinghistory.component';


@NgModule({
  declarations: [
    BookinghistoryComponent
  ],
  imports: [
    CommonModule,
    BookinghistoryRoutingModule
  ]
})
export class BookinghistoryModule { }
