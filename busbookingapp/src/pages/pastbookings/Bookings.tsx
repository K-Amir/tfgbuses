import { IonContent, IonHeader, IonPage } from "@ionic/react";
import { observer } from "mobx-react-lite";
import { useStore } from "../../stores/store";

import "./Bookings.scss";

const Bookings: React.FC = () => {
  const { userStore } = useStore();

  return (
    <IonPage>
      <IonContent class="container" color="light" fullscreen>
        <div className="container">
          {userStore.userInfo === null && <h1>Login to see your tickets</h1>}

          {userStore.userInfo !== null && <h1>Your tickets</h1>}
        </div>
      </IonContent>
    </IonPage>
  );
};

export default observer(Bookings);
