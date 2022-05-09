import React from "react";
import "./Checkout.scss";

import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import PaymentForm from "./PaymentForm";
import { BusTravel } from "../../../../models/BusTravel";

const PUBLIC_KEY: string =
  "pk_test_51KxUWWGEK9krOIWfsukmaj9ycra0PiV6grDI5CCVgwf72m3SMAACovJxvfQBflObH2lErS7A4p2FHpskR4ttCj5s00HoJyV5pJ";

const stripePromise = loadStripe(PUBLIC_KEY);

interface Props {
  setShowCheckout: (value: boolean) => void;
  bus: BusTravel;
}
const Checkout = ({ setShowCheckout, bus }: Props) => {
  return (
    <Elements stripe={stripePromise}>
      <PaymentForm bus={bus} setShowCheckout={setShowCheckout} />
    </Elements>
  );
};

export default Checkout;
