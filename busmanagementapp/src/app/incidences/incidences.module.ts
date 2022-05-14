import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncidencesRoutingModule } from './incidences-routing.module';
import { IndidencesComponent } from './indidences/indidences.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [IndidencesComponent],
  imports: [
    CommonModule,
    IncidencesRoutingModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    SharedModule,
  ],
})
export class IncidencesModule {}
