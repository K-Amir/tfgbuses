import { createContext, useContext } from "react";
import BookingsStore from "./busbookingStore";

interface Store {
  bookingStore: BookingsStore;
}

export const store: Store = {
  bookingStore: new BookingsStore(),
};

export const StoreContext = createContext(store);

export function useStore() {
  return useContext(StoreContext);
}
