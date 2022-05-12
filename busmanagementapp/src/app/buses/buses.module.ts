import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { BusesRoutingModule } from './buses-routing.module';
import { BusesComponent } from './buses/buses.component';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [BusesComponent],
  imports: [
    CommonModule,
    BusesRoutingModule,
    FormsModule,
    NgbModule,
    FontAwesomeModule,
    SharedModule,
  ],
})
export class BusesModule {}
