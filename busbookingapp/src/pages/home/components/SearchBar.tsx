import { IonIcon } from "@ionic/react";
import { paperPlane, searchOutline } from "ionicons/icons";

import "./SearchBar.scss";

const SearchBar: React.FC = () => {
  return (
    <div className="search-bar">
      <div className="search-input">
        <IonIcon class="icon" icon={searchOutline} />
        <input type="text" placeholder="Search a destination..." />
      </div>
      <IonIcon class="search-btn" icon={paperPlane}></IonIcon>
    </div>
  );
};

export default SearchBar;
