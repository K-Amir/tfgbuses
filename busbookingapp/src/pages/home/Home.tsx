import { IonContent } from "@ionic/react";
import React from "react";
import SearchBar from "./components/SearchBar";

const Home: React.FC = () => {
  return (
    <IonContent color="light" fullscreen>
      <div className="container">
        <div className="user-info">
          <div className="greentings">
            <p>Welcome back,</p>
            <p className="bold-name">Jhon</p>
          </div>
          <div className="picture">
            <img src="https://www.fakepersongenerator.com/Face/male/male20161086391719757.jpg" />
          </div>
        </div>
        <SearchBar />
      </div>
    </IonContent>
  );
};

export default Home;
