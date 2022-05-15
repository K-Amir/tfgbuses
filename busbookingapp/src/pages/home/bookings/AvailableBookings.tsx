import { IonContent, IonIcon, useIonRouter } from "@ionic/react";
import { observer } from "mobx-react-lite";
import { arrowBackOutline, sadOutline, ticket } from "ionicons/icons";
import { useStore } from "../../../stores/store";

import "./AvailableBookings.scss";
import Card from "./card/Card";

const Home: React.FC = () => {
  const router = useIonRouter();

  const { bookingStore } = useStore();

  const handleBackBtn = () => {
    router.push("/home");
  };

  return (
    <IonContent color="light" fullscreen>
      <div className="container container-tickets">
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

        {bookingStore.availableBookings.length > 0 && (
          <div className="available-bookings">
            {bookingStore.availableBookings.map((bus) => (
              <Card key={bus.id} bus={bus} />
            ))}
          </div>
        )}
        {!bookingStore.isLoading && bookingStore.availableBookings.length <= 0 && (
          <div className="not-found">
            Sorry, no bookings found for the specified destination
            <IonIcon class="icon" icon={sadOutline}></IonIcon>
          </div>
        )}
      </div>
    </IonContent>
  );
};

export default observer(Home);
