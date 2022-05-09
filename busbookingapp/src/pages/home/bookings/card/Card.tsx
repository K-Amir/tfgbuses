import { IonIcon, useIonRouter } from "@ionic/react";
import { calendarOutline, card, timeOutline } from "ionicons/icons";
import { BusTravel } from "../../../../models/BusTravel";
import { useStore } from "../../../../stores/store";

import "./Card.scss";

interface Props {
  bus: BusTravel;
}

const Card = ({ bus }: Props) => {
  const { userStore } = useStore();
  const router = useIonRouter();

  const handlePurchase = async () => {
    if (userStore.userInfo === null) {
      router.push("/profile");
    }
  };

  return (
    <div className="card">
      <div className="bus-info">
        <div className="destination">
          <div className="hours">
            <p>{bus.hour}</p>
            <p>{bus.arrivalHour}</p>
          </div>
          <div className="symbol">
            <img src="./assets/icon/destination.svg" alt="" />
          </div>
          <div className="cities">
            <p>{bus.origin}</p>
            <p>{bus.destination}</p>
          </div>
        </div>
        <div className="price">{bus.price}€</div>
      </div>
      <div className="short-info">
        <div className="ticket-cut left"></div>
        <div className="ticket-cut right"></div>
        <div className="distance">
          <IonIcon className="icon" icon={calendarOutline}></IonIcon>
          {bus.date}
        </div>
        <div className="duration">
          <IonIcon className="icon" icon={timeOutline}></IonIcon>
          Duration {parseFloat(bus.arrivalHour) - parseFloat(bus.hour)} hours
        </div>
        <div onClick={handlePurchase} className="buy-ticket">
          Buy now
        </div>
      </div>
    </div>
  );
};

export default Card;
