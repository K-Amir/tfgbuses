import { Redirect, Route } from "react-router-dom";
import {
  IonApp,
  IonIcon,
  IonRouterOutlet,
  IonTabBar,
  IonTabButton,
  IonTabs,
  setupIonicReact,
} from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";
import Home from "./pages/home/HomeRouting";
import { bookmark, home, person } from "ionicons/icons";
import "./App.scss";
import { useStore } from "./stores/store";

/* Core CSS required for Ionic components to work properly */
import "@ionic/react/css/core.css";

/* Basic CSS for apps built with Ionic */
import "@ionic/react/css/normalize.css";
import "@ionic/react/css/structure.css";
import "@ionic/react/css/typography.css";

/* Optional CSS utils that can be commented out */
import "@ionic/react/css/padding.css";
import "@ionic/react/css/float-elements.css";
import "@ionic/react/css/text-alignment.css";
import "@ionic/react/css/text-transformation.css";
import "@ionic/react/css/flex-utils.css";
import "@ionic/react/css/display.css";

/* Theme variables */
import "./theme/variables.css";
import Profile from "./pages/profile/Profile";
import Bookings from "./pages/pastbookings/Bookings";
import HomeRouting from "./pages/home/HomeRouting";
import { observer } from "mobx-react-lite";
import { useEffect } from "react";

setupIonicReact();

const App: React.FC = () => {
  const { userStore } = useStore();

  useEffect(() => {
    userStore.loadUser();
  }, []);

  return (
    <IonApp>
      <IonReactRouter>
        <IonTabs>
          <IonRouterOutlet>
            <Route path="/home">
              <HomeRouting />
            </Route>

            <Route exact path="/profile">
              <Profile />
            </Route>

            <Route exact path="/bookings">
              <Bookings />
            </Route>

            <Route exact path="/">
              <Redirect to="/home" />
            </Route>
          </IonRouterOutlet>

          <IonTabBar translucent={true} className="tab-bar " slot="bottom">
            <IonTabButton className="disable-ripple" tab="home" href="/home">
              <IonIcon className="tab-button" icon={home}></IonIcon>
            </IonTabButton>

            {userStore.userInfo !== null && (
              <IonTabButton
                className="disable-ripple"
                tab="bookings"
                href="/bookings"
              >
                <IonIcon className="tab-button " icon={bookmark}></IonIcon>
              </IonTabButton>
            )}

            <IonTabButton
              className="disable-ripple"
              tab="profile"
              href="/profile"
            >
              <IonIcon className="tab-button" icon={person}></IonIcon>
            </IonTabButton>
          </IonTabBar>
        </IonTabs>
      </IonReactRouter>
    </IonApp>
  );
};

export default observer(App);
