import { IonContent, IonPage } from "@ionic/react";
import { observer } from "mobx-react-lite";
import { useEffect, useState } from "react";
import { useStore } from "../../stores/store";
import QRCode from "react-qr-code";

import "./Bookings.scss";
import Ticket from "../../models/Ticket";

const Bookings: React.FC = () => {
  const { userStore } = useStore();

  useEffect(() => {
    userStore.loadUserTickets();
  }, []);

  return (
    <IonPage>
      <IonContent class="container" color="light" fullscreen>
        <div className="container">
          {userStore.userInfo === null && <h1>Login to see your tickets</h1>}

          {userStore.userInfo != null && (
            <div className="tickets">
              <h2>My tickets</h2>
              {userStore.userTickers &&
                userStore.userTickers.map((ticket: Ticket, index: number) => {
                  return (
                    <div className="busthing" key={index}>
                      {ticket.origin} - {ticket.destination}
                      <br />
                      <QRCode
                        size={120}
                        key={index}
                        value={ticket.jwtQr}
                      ></QRCode>
                    </div>
                  );
                })}
            </div>
          )}
        </div>
      </IonContent>
    </IonPage>
  );
};

export default observer(Bookings);
