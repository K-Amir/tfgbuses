import { useState } from "react";
import Ticket from "../../../models/Ticket";
import Details from "../details/Details";
import "./Ticket.scss";

interface Props {
  ticket: Ticket;
}

const TicketItem = ({ ticket }: Props) => {
  const [showDetails, setShowDetails] = useState(false);
  const handleShowQr = () => {
    setShowDetails(true);
  };

  if (showDetails) {
    return <Details ticket={ticket} setShowDetails={setShowDetails} />;
  }

  return (
    <div className="ticket">
      <div className="cities">
        <div className="info">
          <p>
            {ticket.hour} <br /> <br />
            {ticket.arrivalHour}
          </p>
          <img src="./assets/icon/destinate.svg" alt="" />
          <p>
            {ticket.origin}
            <br />
            <br />
            {ticket.destination}
          </p>
        </div>

        <span className="date">{ticket.date}</span>
      </div>
      <div className="showqr">
        <div className="btn" onClick={handleShowQr}>
          Show QR
        </div>
      </div>
    </div>
  );
};

export default TicketItem;
