import {LOGIN_URL, SIGNUP_URL} from "../config/config";

const axios = require("axios").default;


export default class AuthService {

    static signup(credentials) {
        return axios.post(SIGNUP_URL, credentials);
    }

    static login(credentials) {
        return axios.post(LOGIN_URL, credentials);
    }

    static getUserToken(){
        console.log(localStorage.getItem("token"))
        return JSON.parse(localStorage.getItem("token"));
    }

    static getAuthHeader() {
        return {headers: {Authorization: 'Bearer ' + this.getUserToken() }};
    }


    static logOut() {
        localStorage.removeItem("token");
        // return axios.post(USER_API_BASE_URL + 'logout', {}, this.getAuthHeader());
    }

}