import { IonContent, IonPage } from "@ionic/react";
import SearchBar from "./components/SearchBar";

import "./Home.scss";

const Home: React.FC = () => {
  return (
    <IonPage>
      <IonContent color="light" fullscreen>
        <div className="container">
          <SearchBar />
        </div>
      </IonContent>
    </IonPage>
  );
};

export default Home;
