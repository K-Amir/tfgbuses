import { IonPage, IonRouterOutlet } from "@ionic/react";
import { Route } from "react-router-dom";
import AvailableBookings from "./bookings/AvailableBookings";

import Home from "./Home";

import "./Home.scss";

const HomeRouting: React.FC = () => {
  return (
    <IonPage>
      <IonRouterOutlet>
        <Route exact path="/home" component={Home} />
        <Route path="/home/search" component={AvailableBookings} />
      </IonRouterOutlet>
    </IonPage>
  );
};

export default HomeRouting;
