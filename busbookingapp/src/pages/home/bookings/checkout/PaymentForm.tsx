import { IonIcon, useIonRouter } from "@ionic/react";
import {
  CardCvcElement,
  CardElement,
  CardExpiryElement,
  CardNumberElement,
  useElements,
  useStripe,
} from "@stripe/react-stripe-js";

import axios from "axios";
import { closeOutline } from "ionicons/icons";
import { useState } from "react";
import { BusTravel } from "../../../../models/BusTravel";
import { useStore } from "../../../../stores/store";

import "./PaymentForm.scss";

interface Props {
  setShowCheckout: (value: boolean) => void;
  bus: BusTravel;
}

const PaymentForm = ({ setShowCheckout, bus }: Props) => {
  const stripe = useStripe();
  const elements = useElements();
  const { userStore } = useStore();
  const router = useIonRouter();

  const [loading, setLoading] = useState(false);

  const handleFormSubmit = async (e: any) => {
    setLoading(true);
    e.preventDefault();

    const cardElement: any = elements?.getElement(CardElement);
    if (!cardElement) return;

    const { error, paymentMethod } = await stripe!.createPaymentMethod({
      type: "card",
      card: cardElement,
    });

    if (!error) {
      try {
        const { id } = paymentMethod;
        const response = await axios.post(
          "http://localhost:8080/empresa/v0/bookings/buy",
          {
            amount: parseFloat(bus.price) * 100,
            id,
            userEmail: userStore.userInfo?.email,
            busId: parseInt(bus.id),
          },
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("jwt")}`,
            },
          }
        );
        setLoading(false);

        userStore.loadUserTickets();

        if (response.status === 200) {
          router.push("/bookings");
        }
      } catch (error) {
        setLoading(false);
        console.log("error", error);
      }
    }
  };

  return (
    <>
      <form className="checkout" onSubmit={handleFormSubmit}>
        <br />
        <div onClick={() => setShowCheckout(false)} className="close">
          <IonIcon icon={closeOutline}></IonIcon>
        </div>
        <CardElement />
        <br />
        <button disabled={loading}>
          {loading ? (
            <span className="loading">
              Paying...
              <div className="lds-ring">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
              </div>
            </span>
          ) : (
            <span className="pay"> Pay</span>
          )}
        </button>
      </form>
    </>
  );
};

export default PaymentForm;
