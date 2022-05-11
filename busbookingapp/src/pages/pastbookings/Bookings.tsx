import { IonContent, IonIcon, IonPage } from "@ionic/react";
import { observer } from "mobx-react-lite";
import { useEffect } from "react";
import { useStore } from "../../stores/store";

import "./Bookings.scss";
import Ticket from "../../models/Ticket";
import TicketItem from "./ticket/TicketItem";
import { sadOutline } from "ionicons/icons";

const Bookings: React.FC = () => {
  const { userStore } = useStore();

  useEffect(() => {
    userStore.loadUserTickets();
  }, [userStore]);

  return (
    <IonPage>
      <IonContent class="container" color="light" fullscreen>
        <div className="container">
          {userStore.userInfo === null && <h1>Login to see your tickets</h1>}

          {userStore.userInfo != null && (
            <div className="tickets">
              <h2>My tickets</h2>
              {userStore.userTickers && userStore.userTickers.length > 0 ? (
                userStore.userTickers.map((ticket: Ticket, index: number) => {
                  return <TicketItem key={index} ticket={ticket} />;
                })
              ) : (
                <p className="no-ticket">
                  You haven't bought any ticket yet
                  <IonIcon icon={sadOutline}></IonIcon>
                </p>
              )}
            </div>
          )}
        </div>
      </IonContent>
    </IonPage>
  );
};

export default observer(Bookings);
