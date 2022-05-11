import { IonContent, IonPage } from "@ionic/react";
import { observer } from "mobx-react-lite";
import { useState } from "react";
import { useStore } from "../../stores/store";
import Login from "./components/Login";
import Signup from "./components/Signup";
import "./Profile.scss";

const Profile = () => {
  const [toggleForms, setToggleForms] = useState(true);
  const [successMessage, setSuccessMessage] = useState("");

  const { userStore } = useStore();

  if (userStore.userInfo !== null) {
    return (
      <IonPage>
        <IonContent color="light" fullscreen>
          {userStore.userInfo && (
            <div className="container">
              <h4>
                Welcome, back {userStore.userInfo.name}{" "}
                {userStore.userInfo.surname}
              </h4>

              <div className="btn-logout" onClick={userStore.logout}>
                Log out
              </div>
            </div>
          )}
        </IonContent>
      </IonPage>
    );
  }

  return (
    <IonPage>
      <IonContent fullscreen color="light">
        <div className="container">
          {successMessage && (
            <div className="success-message">{successMessage}</div>
          )}
          {toggleForms && (
            <>
              <Login setToggleForms={setToggleForms} />
            </>
          )}

          {!toggleForms && (
            <>
              <Signup
                setToggleForms={setToggleForms}
                setSuccessMessage={setSuccessMessage}
              />
            </>
          )}
        </div>
      </IonContent>
    </IonPage>
  );
};

export default observer(Profile);
