import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BusesRoutingModule } from './buses-routing.module';
import { BusesComponent } from './buses/buses.component';


@NgModule({
  declarations: [
    BusesComponent
  ],
  imports: [
    CommonModule,
    BusesRoutingModule
  ]
})
export class BusesModule { }
