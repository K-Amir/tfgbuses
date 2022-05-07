import { IonContent, IonHeader, IonPage } from "@ionic/react";

import "./Bookings.scss";

const Bookings: React.FC = () => {
  return (
    <IonPage>
      <IonContent class="container" color="light" fullscreen>
        <div className="container">
          {/* If not lgoged in */}
          <h1>Login to see your tickets</h1>
        </div>
      </IonContent>
    </IonPage>
  );
};

export default Bookings;
