import { IonIcon } from "@ionic/react";
import { mapOutline, timeOutline } from "ionicons/icons";
import React from "react";

import "./Card.scss";

const Card: React.FC = () => {
  return (
    <div className="card">
      <div className="bus-info">
        <div className="destination">
          <div className="hours">
            <p>11:00</p>
            <p>19:35</p>
          </div>
          <div className="symbol">
            <img src="./assets/icon/destination.svg" alt="" />
          </div>
          <div className="cities">
            <p>Madrid</p>
            <p>Barcelona</p>
          </div>
        </div>
        <div className="price">62€</div>
      </div>
      <div className="short-info">
        <div className="ticket-cut left"></div>
        <div className="ticket-cut right"></div>
        <div className="distance">
          <IonIcon className="icon" icon={mapOutline}></IonIcon>
          432km
        </div>
        <div className="duration">
          <IonIcon className="icon" icon={timeOutline}></IonIcon>Duration 4
          hours
        </div>
        <div className="buy-ticket">Buy now</div>
      </div>
    </div>
  );
};

export default Card;
