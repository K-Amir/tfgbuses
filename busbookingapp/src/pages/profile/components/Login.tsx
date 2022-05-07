import { IonIcon } from "@ionic/react";
import { keyOutline, lockClosed, mailOutline } from "ionicons/icons";
import React from "react";
import { useForm } from "react-hook-form";
import "./Login.scss";

const Login: React.FC = () => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  const email = watch("email");
  const password = watch("password");

  const handleLogin = (data: any) => {
    // handleLogin
    console.log(data);
  };

  return (
    <div className="login">
      <img src="./assets/icon/personlogin.svg" alt="" />
      <p className="title">Login</p>

      <form onSubmit={handleSubmit((data) => handleLogin(data))}>
        <div className={errors.email ? "input-block has-error" : "input-block"}>
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
          className={errors.password ? "input-block has-error" : "input-block"}
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
    </div>
  );
};

export default Login;
