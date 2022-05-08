import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndidencesComponent } from './indidences/indidences.component';

const routes: Routes = [
  {
    path: '',
    component: IndidencesComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class IncidencesRoutingModule {}
