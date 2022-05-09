import { makeAutoObservable } from "mobx";
import axios, { AxiosResponse } from "axios";

interface UserInfo {
  email: string;
  name: string;
  surname: string;
}

export default class UserStore {
  userInfo: UserInfo | null = null;

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

    if (res.status == 200) {
      let userInfo: UserInfo = res.data;
      this.setUserInfo(userInfo);
    } else {
      this.setUserInfo(null);
    }
  };

  logout = async () => {
    localStorage.removeItem("jwt");
    this.userInfo = null;
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
  };

  setUserInfo(userInfo: UserInfo | null) {
    this.userInfo = userInfo;
  }
}
