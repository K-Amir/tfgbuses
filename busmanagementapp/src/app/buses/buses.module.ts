import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbDate, NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { BusesRoutingModule } from './buses-routing.module';
import { BusesComponent } from './buses/buses.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [BusesComponent],
  imports: [CommonModule, BusesRoutingModule, FormsModule, NgbModule],
})
export class BusesModule {}
