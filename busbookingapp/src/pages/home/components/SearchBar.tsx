import { IonIcon } from "@ionic/react";
import { paperPlane, searchOutline } from "ionicons/icons";
import { useIonRouter } from "@ionic/react";

import "./SearchBar.scss";

const SearchBar: React.FC = () => {
  const router = useIonRouter();

  const handleSearch = () => {
    router.push("/home/search");
  };

  return (
    <div className="search-bar">
      <div className="search-input">
        <IonIcon class="icon" icon={searchOutline} />
        <input type="text" placeholder="Search a destination..." />
      </div>
      <IonIcon
        onClick={handleSearch}
        class="search-btn"
        icon={paperPlane}
      ></IonIcon>
    </div>
  );
};

export default SearchBar;
