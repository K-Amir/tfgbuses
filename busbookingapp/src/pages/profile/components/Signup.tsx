import { IonIcon } from "@ionic/react";
import {
  keyOutline,
  lockClosed,
  mailOutline,
  peopleOutline,
  personOutline,
} from "ionicons/icons";
import { useState } from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import "./Signup.scss";

interface Props {
  setToggleForms: (toggle: boolean) => void;
  setSuccessMessage: (message: string) => void;
}

const Signup = ({ setToggleForms, setSuccessMessage }: Props) => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const email = watch("email");
  const password = watch("password");
  const name = watch("name");
  const surname = watch("surname");

  const [showContent, setShowContent] = useState(false);

  const handleSignup = async (data: any) => {
    setSuccessMessage("");
    await axios({
      method: "POST",
      url: "https://springbackbustravel.herokuapp.com/empresa/v0/auth/app",
      data: {
        ...data,
      },
      headers: {
        "Content-Type": "application/json",
      },
    });

    setSuccessMessage("Registration successful, please log in.");
    setToggleForms(true);
  };

  return (
    <div className="signup">
      <img
        onLoad={() => setShowContent(true)}
        className="paper-sign"
        src="./assets/icon/signup.svg"
        alt="Sign locking with the key and the lock"
      />

      {showContent && (
        <>
          <div className="title">Signup a new account</div>
          <form onSubmit={handleSubmit((data) => handleSignup(data))}>
            <div className="two">
              <div
                className={
                  errors.name ? "input-block has-error" : "input-block"
                }
              >
                <input
                  className={name ? "active" : ""}
                  {...register("name", {
                    required: "Name is required",
                  })}
                />
                <label className={name ? "active" : ""} htmlFor="name">
                  <IonIcon icon={personOutline}></IonIcon>
                  Name
                </label>
                <p className="error">{errors.name?.message}</p>
              </div>

              <div
                className={
                  errors.surname ? "input-block has-error" : "input-block"
                }
              >
                <input
                  className={surname ? "active" : ""}
                  {...register("surname", {
                    required: "Surname is required",
                  })}
                />
                <label className={surname ? "active" : ""} htmlFor="surname">
                  <IonIcon icon={peopleOutline}></IonIcon>
                  Surname
                </label>
                <p className="error">{errors.surname?.message}</p>
              </div>
            </div>

            <div
              className={errors.email ? "input-block has-error" : "input-block"}
            >
              <input
                className={email ? "active" : ""}
                {...register("email", {
                  required: "Email is required",
                  pattern: {
                    value: new RegExp("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"),
                    message: "Email not valid",
                  },
                })}
              />
              <label className={email ? "active" : ""} htmlFor="email">
                <IonIcon icon={mailOutline}></IonIcon>
                Email
              </label>
              <p className="error">{errors.email?.message}</p>
            </div>

            <div
              className={
                errors.password ? "input-block has-error" : "input-block"
              }
            >
              <input
                className={password ? "active" : ""}
                type="password"
                {...register("password", {
                  required: "Password is required",
                })}
              />
              <label className={password ? "active" : ""} htmlFor="password">
                <IonIcon icon={keyOutline}></IonIcon>
                Password
              </label>
              <p className="error">{errors.password?.message}</p>
            </div>

            <button className="search-btn">
              <IonIcon icon={lockClosed}></IonIcon>Signup
            </button>
          </form>
          <p className="toggle-btn" onClick={() => setToggleForms(true)}>
            Already a user? <b>Sign in</b> instead
          </p>
        </>
      )}
    </div>
  );
};

export default Signup;
