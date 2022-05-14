import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { IncidencesService } from '../incidences.service';

@Component({
  selector: 'app-indidences',
  templateUrl: './indidences.component.html',
  styleUrls: ['./indidences.component.scss'],
})
export class IndidencesComponent implements OnInit {
  showAddModal: boolean = false;
  faPlus = faPlus;

  formGroup: FormGroup = new FormGroup({
    reason: new FormControl('', [Validators.required]),
    bus_id: new FormControl('', [
      Validators.required,
      Validators.pattern('[0-9]+'),
    ]),
  });

  loading: boolean = false;

  incidences!: any[];

  labels: string[] = ['Reason', 'Bus Id'];

  constructor(private incidenceService: IncidencesService) {}

  ngOnInit(): void {
    this.loadAllIndidences();
  }

  handleCloseModal() {
    this.showAddModal = false;
  }

  handleFormSubmit() {
    this.formGroup.markAllAsTouched();

    if (this.formGroup.invalid) return;
    this.loading = true;

    this.incidenceService.addIncidence(this.formGroup.value).subscribe({
      error: (e) => {
        this.showAddModal = false;
        this.loadAllIndidences();
        this.loading = false;
      },
      complete: () => {
        this.showAddModal = false;
        this.loadAllIndidences();
        this.loading = false;
      },
    });
  }

  loadAllIndidences() {
    this.incidenceService.getAllIncidences().subscribe({
      next: (v: any) => {
        this.incidences = v;
      },
    });
  }

  showErrorBorder(control: string) {
    return this.formGroup.get(control)?.errors &&
      this.formGroup.get(control)?.touched
      ? 'error'
      : '';
  }
}
