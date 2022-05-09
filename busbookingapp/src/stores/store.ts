import { createContext, useContext } from "react";
import BookingsStore from "./busbookingStore";
import UserStore from "./userStore";

interface Store {
  bookingStore: BookingsStore;
  userStore: UserStore;
}

export const store: Store = {
  bookingStore: new BookingsStore(),
  userStore: new UserStore(),
};

export const StoreContext = createContext(store);

export function useStore() {
  return useContext(StoreContext);
}
