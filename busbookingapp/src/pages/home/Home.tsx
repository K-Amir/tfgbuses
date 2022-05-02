import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
} from "@ionic/react";

import "./Home.css";

const Home: React.FC = () => {
  return (
    <IonPage>
      <IonContent class="container" fullscreen color="light">
        <h1>test</h1>
      </IonContent>
    </IonPage>
  );
};

export default Home;
