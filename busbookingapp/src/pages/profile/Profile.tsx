import { IonContent, IonPage } from "@ionic/react";
import React, { useState } from "react";
import Login from "./components/Login";
import Signup from "./components/Signup";
import "./Profile.scss";

const Profile: React.FC = () => {
  const [toggleForms, setToggleForms] = useState(true);

  return (
    <IonPage>
      <IonContent fullscreen color="light">
        <div className="container">
          {toggleForms && (
            <>
              <Login />
              <p className="toggle-btn" onClick={() => setToggleForms(false)}>
                New user? <b>Sign up</b> instead
              </p>
            </>
          )}

          {!toggleForms && (
            <>
              <Signup />
              <p className="toggle-btn" onClick={() => setToggleForms(true)}>
                Already a user? <b>Sign in</b> instead
              </p>
            </>
          )}
        </div>
      </IonContent>
    </IonPage>
  );
};

export default Profile;
