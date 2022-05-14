import moment from "moment";
import QRCode from "react-qr-code";
import Ticket from "../../../models/Ticket";
import { useStore } from "../../../stores/store";
import "./Details.scss";

interface Props {
  setShowDetails: (value: boolean) => void;
  ticket: Ticket;
}

const Details = ({ setShowDetails, ticket }: Props) => {
  const [day, month, year] = ticket.date.split("-");
  const dateJs = new Date(parseInt(year), parseInt(month) - 1, parseInt(day));

  const date = moment(dateJs);

  const { userStore } = useStore();

  return (
    <div className="details">
      <div className="sub-title" onClick={() => setShowDetails(false)}>
        Your booking details
      </div>
      <div className="wrap">
        <div className="times">
          <p>{ticket.hour}</p>
          <p>{ticket.arrivalHour}</p>
        </div>
        <img
          src="./assets/icon/longqr.svg"
          alt="Indicate the length distance"
        />
        <div className="destinations">
          <p>{ticket.origin}</p>
          <p>{ticket.destination}</p>
        </div>
        <div className="date">
          <p>Date :</p>
          <span>{date.format("MMMM Do YYYY")}</span>
        </div>
        <div className="passenger">
          <p>Passenger :</p>
          <span>{userStore.userInfo?.name}</span>
        </div>
      </div>
      <div className="qr">
        <div className="ball left"></div>
        <div className="ball right"></div>
        <QRCode
          size={160}
          value="kghdfkjghsasdasdasdasjddflkjghsd loiugyhdlf"
        />
      </div>
    </div>
  );
};

export default Details;
