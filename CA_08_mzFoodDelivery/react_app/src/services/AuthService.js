import {GOOGLE_LOGIN_URL, LOGIN_URL, SIGNUP_URL} from "../config/config";
import { http } from './http'

export default class AuthService {

    static signup(credentials) {
        return http.post(SIGNUP_URL, credentials);
    }

    static login(credentials) {
        return http.post(LOGIN_URL, credentials);
    }

    static googleLogin(credentials) {
        return http.post(GOOGLE_LOGIN_URL, credentials);
    }

    static getUserToken(){
        console.log(localStorage.getItem("token"));
        return localStorage.getItem("token");
    }

    static getAuthHeader() {
        return 'Bearer ' + this.getUserToken();
    }


    static logOut() {
        localStorage.removeItem("token");
        // return axios.post(USER_API_BASE_URL + 'logout', {}, this.getAuthHeader());
    }

}