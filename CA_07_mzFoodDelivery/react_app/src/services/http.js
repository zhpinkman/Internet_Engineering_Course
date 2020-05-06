import axios from 'axios';
import AuthService from "./AuthService";
import {toast} from "react-toastify";

let instance = axios.create();
toast.configure({rtl: true, className: "text-center"});

let domain_url = "http://localhost:8080/";
let auth_url = "auth";
// let partyFoods_url = "partyFoods";
// let user_url = "user";
// let restaurants_url = "restaurants";
// let search_url = "search";

instance.interceptors.request.use((config) => {
    if (!config.url.startsWith(domain_url + auth_url)) {
        config.headers = {Authorization: AuthService.getAuthHeader()};
    }
    // axios.defaults.headers.common['Authorization'] = AuthService.getAuthHeader();
    return config;
})

instance.interceptors.response.use(response => {
    return response;
}, error => {
    if (error.response.status === 403 ) {
        window.location = "/login";
    } else {
        return Promise.reject(error);
    }
})

export const http = instance;