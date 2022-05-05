import { IonContent, IonPage } from "@ionic/react";
import React from "react";
import "./Profile.scss";

const Profile: React.FC = () => {
  return (
    <IonPage>
      <IonContent fullscreen color="light">
        <div className="container">
          <h1>test</h1>
        </div>
      </IonContent>
    </IonPage>
  );
};

export default Profile;
