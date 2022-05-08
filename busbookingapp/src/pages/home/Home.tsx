import { IonContent, IonIcon, useIonRouter } from "@ionic/react";
import { calendarOutline, locationOutline, paperPlane } from "ionicons/icons";
import React from "react";
import { useForm } from "react-hook-form";
import { useStore } from "../../stores/store";

const Home: React.FC = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const router = useIonRouter();

  const { bookingStore } = useStore();

  const originCity = watch("originCity");
  const destinationCity = watch("destinationCity");
  const bookingDate = watch("bookingDate");

  const handleSearch = (data: any) => {
    bookingStore.loadAvailableBuses({
      origin: data.originCity,
      destination: data.destinationCity,
      lowerDate: data.bookingDate,
    });
    router.push("/home/search");
  };

  return (
    <IonContent color="light" fullscreen>
      <div className="container">
        <div className="booking-image">
          <img src="./assets/home/mainhome.svg" alt="" />
          <p>Bus travelling</p>
          <span>Search your destination</span>
        </div>

        <form
          className="searching-form"
          onSubmit={handleSubmit((data) => handleSearch(data))}
        >
          <div
            className={
              errors.originCity ? "input-block has-error" : "input-block"
            }
          >
            <input
              className={originCity ? "active" : ""}
              {...register("originCity", {
                required: "Origin is required",
              })}
            />
            <label className={originCity ? "active" : ""} htmlFor="originCity">
              <IonIcon icon={locationOutline}></IonIcon>
              Origin
            </label>
            <p className="error">{errors.originCity?.message}</p>
          </div>

          <div
            className={
              errors.destinationCity ? "input-block has-error" : "input-block"
            }
          >
            <input
              className={destinationCity ? "active" : ""}
              {...register("destinationCity", {
                required: "Destination is required",
              })}
            />
            <label
              className={destinationCity ? "active" : ""}
              htmlFor="originCity"
            >
              <IonIcon icon={locationOutline}></IonIcon>
              Destination
            </label>
            <p className="error">{errors.destinationCity?.message}</p>
          </div>

          <div
            className={
              errors.bookingDate ? "input-block has-error" : "input-block"
            }
          >
            <input
              className={bookingDate ? "active" : ""}
              {...register("bookingDate", {
                required: "Date is required",
              })}
            />
            <label
              className={bookingDate ? "active" : ""}
              htmlFor="bookingDate"
            >
              <IonIcon icon={calendarOutline}></IonIcon>
              Date
            </label>
            <p className="error">{errors.bookingDate?.message}</p>
          </div>

          <button className="search-btn">
            Search
            <IonIcon icon={paperPlane}></IonIcon>
          </button>
        </form>
      </div>
    </IonContent>
  );
};

export default Home;
