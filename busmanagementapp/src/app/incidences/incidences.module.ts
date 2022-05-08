import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncidencesRoutingModule } from './incidences-routing.module';
import { IndidencesComponent } from './indidences/indidences.component';


@NgModule({
  declarations: [
    IndidencesComponent
  ],
  imports: [
    CommonModule,
    IncidencesRoutingModule
  ]
})
export class IncidencesModule { }
