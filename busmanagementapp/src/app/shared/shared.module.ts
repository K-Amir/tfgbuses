import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormmodalComponent } from './formmodal/formmodal.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [FormmodalComponent],
  imports: [CommonModule, FontAwesomeModule, ReactiveFormsModule],
  exports: [FormmodalComponent],
})
export class SharedModule {}
