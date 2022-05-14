import { Component, Injectable, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faCalendar, faPlus } from '@fortawesome/free-solid-svg-icons';
import {
  NgbDateParserFormatter,
  NgbDateStruct,
} from '@ng-bootstrap/ng-bootstrap';
import { BusService } from '../bus.service';

@Injectable()
export class CustomDateParserFormatter extends NgbDateParserFormatter {
  readonly DELIMITER = '-';

  parse(value: string): NgbDateStruct | null {
    if (value) {
      const date = value.split(this.DELIMITER);
      return {
        day: parseInt(date[0]),
        month: parseInt(date[1]),
        year: parseInt(date[2]),
      };
    }
    return null;
  }

  format(date: NgbDateStruct | null): string {
    return date
      ? (date.day < 10 ? '0' + date.day : date.day) +
          this.DELIMITER +
          (date.month < 10 ? '0' + date.month : date.month) +
          this.DELIMITER +
          date.year
      : '';
  }
}

@Component({
  selector: 'app-buses',
  templateUrl: './buses.component.html',
  styleUrls: ['./buses.component.scss'],
  providers: [
    {
      provide: NgbDateParserFormatter,
      useClass: CustomDateParserFormatter,
    },
  ],
})
export class BusesComponent implements OnInit {
  // faIcons
  faPlus = faPlus;
  faCalendar = faCalendar;

  // toggles
  showAddModal: boolean = false;
  loading: boolean = false;

  // forms
  model!: NgbDateStruct;

  // price!: string;
  // available_seats!: string;

  formGroup: FormGroup = new FormGroup({
    origin: new FormControl('', [Validators.required]),
    destination: new FormControl('', [Validators.required]),
    hour: new FormControl('', [
      Validators.required,
      Validators.pattern('([0-1][0-9]|2[0-3]):[0-5][0-9]'),
    ]),
    date: new FormControl('', [Validators.required]),
    arrivalHour: new FormControl('', [
      Validators.required,
      Validators.pattern('([0-1][0-9]|2[0-3]):[0-5][0-9]'),
    ]),
    price: new FormControl('', [
      Validators.required,
      Validators.pattern('[0-9]{0,8}(\\.[0-9]{1,2})?$'),
    ]),
    availableSeats: new FormControl('', [
      Validators.required,
      Validators.pattern('^([1-9]|[1-9][0-9]|[1][0-9][0-9]|20[0-0])$'),
    ]),
  });

  // busesArray
  buses!: any;

  // formLabels
  labels: string[] = [
    'Origin',
    'Destination',
    'Date',
    'Depart hour',
    'Arrival hour',
    'Price',
    'Available seats',
  ];

  constructor(private busService: BusService) {}

  handleCloseModal() {
    this.showAddModal = false;
  }

  handleFormSubmit() {
    this.formGroup.markAllAsTouched();

    if (this.formGroup.invalid) return;
    this.loading = true;

    this.busService
      .addBus({
        ...this.formGroup.value,
        origin: this.capitalizeFirstLetter(this.formGroup.get('origin')?.value),
        date: `${this.formGroup.get('date')?.value.day}-${
          this.formGroup.get('date')?.value.month
        }-${this.formGroup.get('date')?.value.year}`,
        destination: this.capitalizeFirstLetter(
          this.formGroup.get('destination')?.value
        ),
      })
      .subscribe({
        complete: () => {
          this.loadAllBuses();
          this.loading = false;
          this.showAddModal = false;
        },
        error: () => {
          this.loading = false;
          this.showAddModal = false;
        },
      });
  }

  ngOnInit(): void {
    this.loadAllBuses();
  }

  handleDelete(id: string) {
    this.busService.deleteBusById(id).subscribe({
      complete: () => {
        this.loadAllBuses();
      },
    });
  }

  loadAllBuses() {
    this.busService.getAllBuses().subscribe({
      next: (v: any) => {
        this.buses = v;
      },
    });
  }

  showErrorBorder(control: string) {
    return this.formGroup.get(control)?.errors &&
      this.formGroup.get(control)?.touched
      ? 'error'
      : '';
  }

  private capitalizeFirstLetter(text: string) {
    text = text.toLocaleLowerCase();
    return text.charAt(0).toUpperCase() + text.slice(1);
  }
}
