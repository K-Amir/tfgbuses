import { makeAutoObservable } from "mobx";
import axios, { AxiosResponse } from "axios";
import Ticket from "../models/Ticket";

interface UserInfo {
  email: string;
  name: string;
  surname: string;
}

export default class UserStore {
  userInfo: UserInfo | null = null;
  userTickers: Ticket[] | null = [];

  constructor() {
    makeAutoObservable(this);
  }

  loadUser = async () => {
    const token = localStorage.getItem("jwt");
    if (!token) return;

    let res: AxiosResponse = await axios({
      method: "GET",
      url: "http://localhost:8080/empresa/v0/auth/token/" + token,
    });

    if (res.status === 200) {
      let userInfo: UserInfo = res.data;
      this.setUserInfo(userInfo);
    } else {
      this.setUserInfo(null);
    }
  };

  loadUserTickets = async () => {
    const jwt = localStorage.getItem("jwt");
    if (!jwt) return;
    const res = await axios.get(
      `http://localhost:8080/empresa/v0/bookings/${jwt}`,
      {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      }
    );
    this.setUserTickers(res.data);
  };

  setUserTickers = (userTickers: any) => {
    this.userTickers = userTickers;
  };

  logout = async () => {
    localStorage.removeItem("jwt");
    this.userInfo = null;
    this.userTickers = null;
  };

  loginUser = async (password: string, email: string) => {
    const response: AxiosResponse = await axios({
      method: "GET",
      url: "http://localhost:8080/empresa/v0/auth/token",
      headers: {
        user: email,
        password: password,
      },
    });
    const jwtToken: string = response.data;
    localStorage.setItem("jwt", jwtToken);
    this.loadUser();
    this.loadUserTickets();
  };

  setUserInfo(userInfo: UserInfo | null) {
    this.userInfo = userInfo;
  }
}
