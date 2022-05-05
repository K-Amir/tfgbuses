import { IonContent, IonIcon, useIonRouter } from "@ionic/react";
import { arrowBackOutline, filterOutline } from "ionicons/icons";

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
          <IonIcon className="icon filter-icon" icon={filterOutline}></IonIcon>
        </div>
        <div className="available-bookings">
          <Card />
          <Card />
          <Card />
        </div>
      </div>
    </IonContent>
  );
};

export default Home;
