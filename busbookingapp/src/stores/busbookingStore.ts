import { makeAutoObservable } from "mobx";
import { BusTravel } from "../models/BusTravel";
import axios from "axios";

interface Filters {
  lowerDate: string;
  destination: string;
  origin: string;
}

export default class BookingsStore {
  availableBookings: BusTravel[] = [];
  filters!: Filters;
  isLoading: boolean = false;

  constructor() {
    makeAutoObservable(this);
  }

  loadAvailableBuses = async (dateToSearch: Filters) => {
    this.isLoading = true;
    this.availableBookings = [];
    this.setFilters(dateToSearch);
    const availableBookingsServer = await axios.get(
      "https://springbackbustravel.herokuapp.com/empresa/v0/buses/available",
      {
        params: {
          ...this.filters,
        },
      }
    );

    this.isLoading = false;

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
