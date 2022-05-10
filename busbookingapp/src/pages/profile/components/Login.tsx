import { IonIcon, useIonRouter } from "@ionic/react";
import { keyOutline, lockClosed, mailOutline } from "ionicons/icons";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import "./Login.scss";
import { useStore } from "../../../stores/store";
import { observer } from "mobx-react-lite";

interface Props {
  setToggleForms: (value: boolean) => void;
}
const Login = ({ setToggleForms }: Props) => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const [error, setError] = useState("");
  const email = watch("email");
  const password = watch("password");
  const { userStore } = useStore();
  const [showContent, setShowContent] = useState(false);
  const router = useIonRouter();

  const handleLogin = (data: any) => {
    userStore.loginUser(data.password, data.email);
    if (userStore.userInfo !== null) {
      router.push("/bookings");
    } else {
      setError("Wrong credentials");
    }
  };

  return (
    <div className="login">
      {error && <div className="error-msg">{error}</div>}
      <img
        onLoad={() => setShowContent(true)}
        src="./assets/icon/personlogin.svg"
        alt=""
      />
      {showContent && (
        <>
          <p className="title">Login</p>

          <form onSubmit={handleSubmit((data) => handleLogin(data))}>
            <div
              className={errors.email ? "input-block has-error" : "input-block"}
            >
              <input
                className={email ? "active" : ""}
                {...register("email", {
                  required: "Email is required",
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
              <IonIcon icon={lockClosed}></IonIcon>Login
            </button>
          </form>
          <p className="toggle-btn" onClick={() => setToggleForms(false)}>
            New user? <b>Sign up</b> instead
          </p>
        </>
      )}
    </div>
  );
};

export default observer(Login);
