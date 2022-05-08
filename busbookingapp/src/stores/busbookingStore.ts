import { makeAutoObservable } from "mobx";
import { BusTravel } from "../models/BusTravel";
import axios from "axios";

interface SearchBusData {
  origin: string;
  destination: string;
  date: string;
}

interface Filters {
  lowerDate: string;
  destination: string;
  origin: string;
}

export default class BookingsStore {
  availableBookings: BusTravel[] = [];
  filters!: Filters;

  constructor() {
    makeAutoObservable(this);
  }

  loadAvailableBuses = async (dateToSearch: Filters) => {
    this.availableBookings = [];
    this.setFilters(dateToSearch);
    const availableBookingsServer = await axios.get(
      "http://localhost:8080/empresa/v0/buses/available",
      {
        params: {
          ...this.filters,
        },
      }
    );
    console.log(availableBookingsServer.data);
    if (!availableBookingsServer.data) return;

    this.setAvailableBookings(availableBookingsServer.data);
  };

  private setFilters(filters: Filters) {
    this.filters = { ...filters };
  }

  private setAvailableBookings = (bookingsFromServer: BusTravel[]) => {
    bookingsFromServer.forEach((x: BusTravel) =>
      this.availableBookings.push(x)
    );
  };
}
