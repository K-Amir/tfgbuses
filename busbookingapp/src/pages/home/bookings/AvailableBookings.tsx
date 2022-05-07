import { IonContent, IonIcon, useIonRouter } from "@ionic/react";
import { arrowBackOutline, ticket } from "ionicons/icons";

import "./AvailableBookings.scss";
import Card from "./card/Card";

const Home: React.FC = () => {
  const router = useIonRouter();

  const handleBackBtn = () => {
    router.goBack();
  };

  return (
    <IonContent color="light" fullscreen>
      <div className="container">
        <div className="options">
          <IonIcon
            onClick={handleBackBtn}
            className="icon"
            icon={arrowBackOutline}
          ></IonIcon>
          <p>
            Available tickets <IonIcon icon={ticket}></IonIcon>{" "}
          </p>
          <div className="wrap"></div>
        </div>
        <div className="available-bookings">
          <Card />
          <Card />
          <Card />
          <Card />
          <Card />
        </div>
      </div>
    </IonContent>
  );
};

export default Home;
