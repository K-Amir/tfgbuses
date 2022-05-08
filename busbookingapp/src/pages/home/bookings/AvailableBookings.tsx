import { IonContent, IonIcon, useIonRouter } from "@ionic/react";
import { observer } from "mobx-react-lite";
import { arrowBackOutline, ticket } from "ionicons/icons";
import { useStore } from "../../../stores/store";

import "./AvailableBookings.scss";
import Card from "./card/Card";

const Home: React.FC = () => {
  const router = useIonRouter();

  const { bookingStore } = useStore();

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

        {bookingStore.availableBookings && (
          <div className="available-bookings">
            {bookingStore.availableBookings.map((bus) => (
              <Card key={bus.id} bus={bus} />
            ))}
          </div>
        )}
      </div>
    </IonContent>
  );
};

export default observer(Home);
