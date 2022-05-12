import { Component, Injectable, OnInit } from '@angular/core';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
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
        day: parseInt(date[0], 10),
        month: parseInt(date[1], 10),
        year: parseInt(date[2], 10),
      };
    }
    return null;
  }

  format(date: NgbDateStruct | null): string {
    return date
      ? date.day + this.DELIMITER + date.month + this.DELIMITER + date.year
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
  showAddModal: boolean = false;
  model!: NgbDateStruct;
  origin!: string;
  destination!: string;
  hour!: string;
  arrival_hour!: string;
  price!: string;
  available_seats!: string;

  faPlus = faPlus;

  buses!: any;

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
    this.busService
      .addBus({
        hour: this.hour,
        origin: this.origin,
        date: `${this.model.day}-${this.model.month}-${this.model.year}`,
        arrivalHour: this.arrival_hour,
        price: this.price,
        destination: this.destination,
        availableSeats: this.available_seats,
      })
      .subscribe({
        next: (v) => {
          console.log(v);
        },
        complete: () => {
          this.loadAllBuses();
        },
      });
    this.showAddModal = false;
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
        console.log(v);
      },
    });
  }
}
