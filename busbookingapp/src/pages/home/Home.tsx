import { IonContent, IonIcon, useIonRouter } from "@ionic/react";
import { calendarOutline, locationOutline, paperPlane } from "ionicons/icons";
import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useStore } from "../../stores/store";
import DatePicker from "react-multi-date-picker";

const Home: React.FC = () => {
  const {
    register,

    handleSubmit,
    watch,
    setValue,
    formState: { errors },
  } = useForm();

  const router = useIonRouter();
  const { bookingStore } = useStore();

  const originCity = watch("originCity");
  const destinationCity = watch("destinationCity");
  const bookingDate = watch("bookingDate");

  const [showContent, setShowContent] = useState(false);

  const handleSearch = (data: any) => {
    bookingStore.loadAvailableBuses({
      origin: data.originCity,
      destination: data.destinationCity,
      lowerDate: data.bookingDate,
    });
    router.push("/home/search");
  };

  const handleDateChange = (value: any) => {
    setValue("bookingDate", value.format("DD/MM/YYYY"));
  };

  return (
    <IonContent color="light" fullscreen>
      <div className="container">
        <div className="booking-image">
          <img
            onLoad={() => setShowContent(true)}
            src="./assets/home/mainhome.svg"
            alt="Girl and two buses in a bus station"
          />
        </div>
        {showContent && (
          <>
            <div className="home-title">
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
                <label
                  className={originCity ? "active" : ""}
                  htmlFor="originCity"
                >
                  <IonIcon icon={locationOutline}></IonIcon>
                  Origin
                </label>
                <p className="error">{errors.originCity?.message}</p>
              </div>

              <div
                className={
                  errors.destinationCity
                    ? "input-block has-error"
                    : "input-block"
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

                <DatePicker
                  format="DD/MM/YYYY"
                  containerClassName="date-picker"
                  onChange={handleDateChange}
                />

                <p className="error">{errors.bookingDate?.message}</p>
              </div>

              <button className="search-btn">
                Search
                <IonIcon icon={paperPlane}></IonIcon>
              </button>
            </form>
          </>
        )}
      </div>
    </IonContent>
  );
};

export default Home;
